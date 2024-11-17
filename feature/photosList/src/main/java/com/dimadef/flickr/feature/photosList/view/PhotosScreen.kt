package com.dimadef.flickr.feature.photosList.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.dimadef.flickr.feature.photosList.R
import com.dimadef.flickr.feature.photosList.view.preview.PhotosScreenPreviewProvider
import com.dimadef.flickr.feature.photosList.viewmodel.PhotosContract
import com.dimadef.flickr.model.Photo
import com.dimadef.flickr.ui.component.PhotoCard
import com.dimadef.flickr.ui.component.pullrefresh.PullRefreshIndicator
import com.dimadef.flickr.ui.component.pullrefresh.rememberPullToRefreshState
import com.dimadef.flickr.ui.component.state.ErrorComponent
import com.dimadef.flickr.ui.component.state.LoadingComponent
import com.dimadef.flickr.ui.extensions.OnBottomReached
import com.dimadef.flickr.ui.extensions.localMessage
import com.dimadef.flickr.ui.state.PagingState
import com.dimadef.flickr.ui.theme.Dimensions
import com.dimadef.flickr.ui.theme.FlickrTheme
import kotlinx.collections.immutable.ImmutableList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhotosScreen(
    screenState: PhotosContract.State,
    onPhotoClick: (Photo) -> Unit,
    onLoadMore: () -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState(
        refreshing = screenState.photos.refreshing,
        onRefresh = onRefresh,
        enabled = screenState.photos.canRefresh
    )
    val gridState = rememberLazyGridState()
    Box(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        Scaffold(
            topBar = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { paddingValues ->
            val photosState = screenState.photos
            LazyVerticalGrid(
                contentPadding = PaddingValues(all = 24.dp),
                columns = GridCells.Adaptive(160.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                state = gridState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                photoItems(
                    photos = photosState.data,
                    onItemClick = onPhotoClick
                )
                when (photosState.status) {
                    PagingState.Status.Failure ->
                        photosState.throwable?.let { errorItem(it, onRetry) }

                    PagingState.Status.Loading ->
                        loadingItem()

                    else -> {}
                }
            }
        }
        PullRefreshIndicator(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

    gridState.OnBottomReached(buffer = 2) {
        onLoadMore()
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyGridScope.photoItems(
    photos: ImmutableList<Photo>,
    onItemClick: (Photo) -> Unit,
) {
    items(photos, contentType = { "image-card" }) { item ->
        PhotoCard(
            photo = item,
            modifier = Modifier
                .animateItemPlacement()
                .padding(Dimensions.xSmall)
                .clickable { onItemClick(item) }
        )
    }
}

private fun LazyGridScope.loadingItem() {
    item(
        span = { GridItemSpan(maxLineSpan) },
        contentType = "loading"
    ) {
        LoadingComponent(
            modifier = Modifier.padding(Dimensions.xxLarge)
        )
    }
}

private fun LazyGridScope.errorItem(throwable: Throwable, onRetryClick: () -> Unit) {
    item(
        span = { GridItemSpan(maxLineSpan) },
        contentType = "error"
    ) {
        ErrorComponent(
            message = throwable.localMessage,
            onActionClick = onRetryClick,
            modifier = Modifier.padding(Dimensions.xxLarge)
        )
    }
}

@Composable
@Preview
private fun SearchScreenPreview(
    @PreviewParameter(PhotosScreenPreviewProvider::class) state: PhotosContract.State,
) {
    FlickrTheme {
        PhotosScreen(
            screenState = state,
            onPhotoClick = {},
            onLoadMore = {},
            onRefresh = {},
            onRetry = {}
        )
    }
}
