package com.dimadef.flickr.feature.photosList.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dimadef.flickr.feature.photosList.view.PhotosRoute

const val ROUTE_SEARCH = "search_root"

fun NavGraphBuilder.photosGraph(
    navigateToPhotoDetail: (
        url: String,
        title: String?,
        description: String?
    ) -> Unit,
) {
    composable(route = ROUTE_SEARCH) {
        PhotosRoute(navigateToPhotoDetail)
    }
}
