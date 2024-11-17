package com.dimadef.flickr.feature.photosList.view.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dimadef.flickr.feature.photosList.viewmodel.PhotosContract
import com.dimadef.flickr.model.Photo
import com.dimadef.flickr.ui.state.PagingState
import kotlinx.collections.immutable.persistentListOf

internal class PhotosScreenPreviewProvider : PreviewParameterProvider<PhotosContract.State> {
    private val photo = Photo("1", "title", "desc", "", null)
    private val photos = persistentListOf(
        photo,
        photo.copy(id = "2"),
        photo.copy(id = "3"),
        photo.copy(id = "4")
    )
    override val values: Sequence<PhotosContract.State> = sequenceOf(
        PhotosContract.State(
            photos = PagingState(
                data = persistentListOf(),
                status = PagingState.Status.Success
            ),
        ),
        PhotosContract.State(
            photos = PagingState(
                data = photos,
                status = PagingState.Status.Success
            ),
        ),

        PhotosContract.State(
            photos = PagingState(
                data = photos,
                status = PagingState.Status.Refreshing
            ),
        ),
        PhotosContract.State(
            photos = PagingState(
                data = photos,
                status = PagingState.Status.Loading
            ),
        ),
        PhotosContract.State(
            photos = PagingState(
                data = photos,
                status = PagingState.Status.Failure,
                throwable = Throwable("some error happened")
            ),
        )
    )
}
