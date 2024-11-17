package com.dimadef.flickr.feature.photosList.viewmodel

import com.dimadef.flickr.model.Photo
import com.dimadef.flickr.ui.state.PagingState
import javax.annotation.concurrent.Immutable

internal object PhotosContract {
    sealed interface Intent {
        data object OnLoadMore : Intent
        data object OnRefresh : Intent
        data object OnRetry : Intent
    }

    @Immutable
    data class State(
        val photos: PagingState<Photo> = PagingState(),
    )
}
