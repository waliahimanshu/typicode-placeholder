package com.waliahimanshu.android.feature.albums

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import com.waliahimanshu.android.feature.photos.R

@Composable
fun AlbumList(lazyPagingItems: LazyPagingItems<AlbumDetailUIModel>) { // inject viewmodel here

    LazyColumn {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.albumId }) { index ->
            val item = checkNotNull(lazyPagingItems[index])
            AlbumItem(item = item)
        }
    }
}

@Composable
fun AlbumItem(item: AlbumDetailUIModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            // todo load image url
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberVectorPainter(
                    image = ImageVector.vectorResource(
                        R.drawable.baseline_image_24
                    )
                ),
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = item.photoTitle)
                Text(text = item.albumTitle, fontWeight = FontWeight.Bold)
                Text(modifier = Modifier.padding(top = 4.dp), text = item.userName)
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AlbumItemListPreview() {
    AppTheme {
        AlbumItem(
            AlbumDetailUIModel(
                albumId = 1,
                albumTitle = "albumTitle",
                userName = "userName",
                photoTitle = "photoTitle",
                thumbnailUrl = "url"
            )
        )
    }
}