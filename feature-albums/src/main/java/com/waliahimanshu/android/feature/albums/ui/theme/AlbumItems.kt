package com.waliahimanshu.android.feature.albums.ui.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.waliahimanshu.android.feature.albums.AlbumsViewModel
import com.waliahimanshu.android.feature.albums.ui.screens.AlbumItemCard
import com.waliahimanshu.android.feature.albums.ui.theme.loading.ErrorItem
import com.waliahimanshu.android.feature.albums.ui.theme.loading.LoadingItem
import com.waliahimanshu.android.feature.photos.R

@Composable
fun AlbumList(viewModel: AlbumsViewModel) {

    val lazyPagingItems = viewModel.albumItems.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.albumId }) { index ->
            val item = checkNotNull(lazyPagingItems[index])
            AlbumItemCard(item = item)
        }

        when (lazyPagingItems.loadState.append) {
            is LoadState.Error -> {
                item {
                    ErrorItem(
                        message = stringResource(id = R.string.generic_error_message),
                        retry = { lazyPagingItems.refresh() },
                    )
                }
            }

            LoadState.Loading -> item { LoadingItem() }
            is LoadState.NotLoading -> Unit
        }
    }
}