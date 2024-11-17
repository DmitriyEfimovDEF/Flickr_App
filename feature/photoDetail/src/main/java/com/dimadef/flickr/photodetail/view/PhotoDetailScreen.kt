package com.dimadef.flickr.photodetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.dimadef.flickr.photodetail.navigation.PhotoDetailArgs
import com.dimadef.flickr.photodetail.view.components.PhotoDetailTopAppBar
import com.dimadef.flickr.ui.theme.Dimensions
import com.dimadef.flickr.ui.theme.FlickrTheme
import com.dimadef.flickr.ui.widget.AsyncImage

@Composable
internal fun PhotoDetailScreen(photo: PhotoDetailArgs, onBackPressed: () -> Unit) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { PhotoDetailTopAppBar(onBackPressed) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues.calculateBottomPadding())
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                url = photo.url,
                description = photo.description,
                contentScale = ContentScale.Fit
            )

            photo.title?.takeIf { it.isNotBlank() }?.let {
                PhotoInfo(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    title = it,
                    description = photo.description
                )
            }
        }
    }
}

@Composable
private fun PhotoInfo(
    title: String,
    description: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent),
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f
                )
            )
            .padding(Dimensions.large)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth()
        )
        description?.let {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PhotoDetailScreenPreview() {
    FlickrTheme {
        PhotoDetailScreen(
            onBackPressed = {},
            photo = PhotoDetailArgs(
                url = "",
                title = "title",
                description = "description"
            )
        )
    }
}
