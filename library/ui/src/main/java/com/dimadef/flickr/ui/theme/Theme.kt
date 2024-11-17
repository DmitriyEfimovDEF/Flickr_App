package com.dimadef.flickr.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.dimadef.flickr.ui.theme.colors.palletDark

@Composable
fun FlickrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
        colorScheme = palletDark
    )
}
