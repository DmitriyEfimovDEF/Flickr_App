package com.dimadef.flickr.photodetail.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimadef.flickr.library.ui.R as UiR
import com.dimadef.flickr.ui.theme.Dimensions
import com.dimadef.flickr.ui.theme.FlickrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhotoDetailTopAppBar(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
            )
        ),
        title = {},
        navigationIcon = {
            Image(
                painter = painterResource(id = UiR.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onBackPressed() }
                    .padding(Dimensions.large)
                    .size(24.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview
@Composable
private fun PhotoDetailTopAppBarPreview() {
    FlickrTheme {
        PhotoDetailTopAppBar(
            onBackPressed = {}
        )
    }
}
