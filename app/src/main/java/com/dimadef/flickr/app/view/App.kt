package com.dimadef.flickr.app.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.dimadef.flickr.app.navigation.FindrNaveHost
import com.dimadef.flickr.ui.theme.FlickrTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    ProvideWindowInsets {
        FlickrTheme {
            val navController = rememberNavController()
            Scaffold(
                modifier = modifier,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) { padding ->
                FindrNaveHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                )
            }
        }
    }
}
