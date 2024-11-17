package com.dimadef.flickr.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dimadef.flickr.feature.photosList.navigation.ROUTE_SEARCH
import com.dimadef.flickr.feature.photosList.navigation.photosGraph
import com.dimadef.flickr.photodetail.navigation.navigateToPhotoDetail
import com.dimadef.flickr.photodetail.navigation.photoDetailGraph

@Composable
fun FindrNaveHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SEARCH,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        photosGraph(
            navigateToPhotoDetail = navController::navigateToPhotoDetail
        )
        photoDetailGraph(
            navController = navController
        )
    }
}
