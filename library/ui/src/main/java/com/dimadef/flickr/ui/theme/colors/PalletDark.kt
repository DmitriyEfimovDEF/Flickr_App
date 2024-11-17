package com.dimadef.flickr.ui.theme.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val palletDark: ColorScheme
    get() = darkColorScheme(
        primary = FlickrColor.DarkBackground,
        onPrimary = FlickrColor.Silver,
        secondary = FlickrColor.BrandColor,
        onSecondary = Color.White,
        tertiary = FlickrColor.Grey20,
        onTertiary = FlickrColor.Grey90,
        primaryContainer = FlickrColor.LightBackground,
        onPrimaryContainer = FlickrColor.TextDark,
        secondaryContainer = FlickrColor.Grey50.copy(alpha = 0.5f),
        onSecondaryContainer = FlickrColor.TextDark,
        background = FlickrColor.DarkBackground,
        onBackground = FlickrColor.TextLight,
        error = FlickrColor.Orange,
        onError = Color.White,
        surface = FlickrColor.DarkBackground,
        onSurface = FlickrColor.TextLight
    )
