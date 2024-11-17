package com.dimadef.flickr.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dimadef.flickr.library.ui.R

@Composable
fun AsyncImage(
    url: String,
    description: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .placeholder(R.color.grey_light)
            .build(),
        error = painterResource(R.drawable.ic_error),
        contentDescription = description,
        contentScale = contentScale,
        modifier = modifier
    )
}
