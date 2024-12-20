package com.dimadef.flickr.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimadef.flickr.ui.theme.Dimensions
import com.dimadef.flickr.ui.theme.FlickrTheme
import com.dimadef.flickr.ui.widget.AsyncImage
import com.dimadef.flickr.model.Photo

@Composable
fun PhotoCard(
    photo: Photo,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f),
            url = photo.thumbnailUrl ?: photo.url,
            description = photo.description
        )
        photo.title?.takeIf { it.isNotBlank() }?.let {
            TitleBar(
                title = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun TitleBar(title: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    )
                )
                .padding(Dimensions.small)
                .fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PhotoCardPreview() {
    FlickrTheme {
        PhotoCard(
            photo = Photo(
                id = "1",
                title = "title",
                description = "description",
                url = "",
                thumbnailUrl = ""
            ),
            modifier = Modifier.width(250.dp)
        )
    }
}
