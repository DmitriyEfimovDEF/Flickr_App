package com.dimadef.flickr.feature.photosList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimadef.flickr.domain.search.PhotosRepository
import com.dimadef.flickr.library.arch.MviViewModel
import com.dimadef.flickr.model.Photo
import com.dimadef.flickr.ui.state.PagingState
import com.dimadef.flickr.ui.state.onFailure
import com.dimadef.flickr.ui.state.onLoading
import com.dimadef.flickr.ui.state.onRefresh
import com.dimadef.flickr.ui.state.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class PhotosViewModel @Inject internal constructor(
    private val photosRepository: PhotosRepository,
) : MviViewModel<PhotosContract.Intent, PhotosContract.State>, ViewModel() {

    private val searchRequestsChannel = Channel<UpdateIntent>(capacity = BUFFERED)
    private val _state = MutableStateFlow(PhotosContract.State())
    override val state: StateFlow<PhotosContract.State> = _state

    override fun onIntent(intent: PhotosContract.Intent) {
        viewModelScope.launch {
            when (intent) {
                is PhotosContract.Intent.OnLoadMore -> onLoadMorePhotos()
                is PhotosContract.Intent.OnRefresh -> onRefresh()
                is PhotosContract.Intent.OnRetry -> onLoadMorePhotos()
            }
        }
    }

    init {
        searchRequestsChannel
            .consumeAsFlow()
            .flatMapLatest(::loadPhotos)
            .onEach { newPagingState -> _state.update { it.copy(photos = newPagingState) } }
            .launchIn(viewModelScope)
        viewModelScope.launch { onRefresh() }
    }

    private suspend fun onLoadMorePhotos() = with(state.value) {
        photos.nextPage?.let {
            searchRequestsChannel.send(UpdateIntent(it))
        }
    }

    private suspend fun onRefresh() {
        searchRequestsChannel.send(UpdateIntent(refreshing = true))
    }

    private fun loadPhotos(intent: UpdateIntent): Flow<PagingState<Photo>> = flow {
        if (intent.refreshing) {
            emit(state.value.photos.onRefresh())
        } else {
            emit(state.value.photos.onLoading(intent.pageNumber))
        }
        photosRepository.getRecentPhotos(intent.pageNumber, PAGE_SIZE)
            .onSuccess { newPage -> emit(state.value.photos.onSuccess(newPage)) }
            .onFailure { throwable -> emit(state.value.photos.onFailure(throwable)) }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}

private data class UpdateIntent(
    val pageNumber: Int = 0,
    val refreshing: Boolean = false,
)