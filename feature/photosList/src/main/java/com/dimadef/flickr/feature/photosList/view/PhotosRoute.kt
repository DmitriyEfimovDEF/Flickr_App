package com.dimadef.flickr.feature.photosList.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimadef.flickr.feature.photosList.viewmodel.PhotosViewModel
import com.dimadef.flickr.feature.photosList.viewmodel.PhotosContract.Intent as SearchIntent

@Composable
internal fun PhotosRoute(
    navigateToPhotoDetail: (
        url: String,
        title: String?,
        description: String?,
    ) -> Unit,
    photosViewModel: PhotosViewModel = hiltViewModel(),
) {
    val searchState by photosViewModel.state.collectAsStateWithLifecycle()

    PhotosScreen(
        screenState = searchState,
        onPhotoClick = { navigateToPhotoDetail(it.url, it.title, it.description) },
        onLoadMore = { photosViewModel.onIntent(SearchIntent.OnLoadMore) },
        onRefresh = { photosViewModel.onIntent(SearchIntent.OnRefresh) },
        onRetry = { photosViewModel.onIntent(SearchIntent.OnRetry) }
    )
}
