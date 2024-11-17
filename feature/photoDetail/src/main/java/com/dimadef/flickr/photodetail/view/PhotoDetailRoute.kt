package com.dimadef.flickr.photodetail.view

import androidx.compose.runtime.Composable
import com.dimadef.flickr.photodetail.navigation.PhotoDetailArgs

@Composable
internal fun PhotoDetailRoute(state: PhotoDetailArgs, onBackPressed: () -> Unit) {
    PhotoDetailScreen(state, onBackPressed)
}
