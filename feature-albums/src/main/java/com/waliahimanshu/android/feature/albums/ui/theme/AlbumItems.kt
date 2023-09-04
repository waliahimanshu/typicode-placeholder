package com.waliahimanshu.android.feature.albums.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.waliahimanshu.android.feature.albums.AlbumsViewModel
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel
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
            AlbumItem(item = item)
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

@Composable
fun AlbumItem(item: AlbumDetailUIModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnailUrl)
                    .build(),
                placeholder = painterResource(R.drawable.baseline_image_24),
                contentDescription = stringResource(R.string.content_description_main_image),
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.photoTitle, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Text(
                    text = item.albumTitle,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(text = item.userName, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
fun AlbumItemCardPreview() {
    AppTheme {
        AlbumItem(
            AlbumDetailUIModel(
                albumId = 1,
                albumTitle = "non esse culpa molestiae omnis sed optio optimonsfhkefjh",
                userName = "Kurtis Weissnatr",
                photoTitle = "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
                thumbnailUrl = "url"
            )
        )
    }
}