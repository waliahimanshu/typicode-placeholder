package com.waliahimanshu.android.feature.albums.ui.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import com.waliahimanshu.android.feature.photos.R

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(68.dp),
                strokeWidth = 4.dp,
            )
        }
    }
}

@Composable
fun ErrorItem(message: String, modifier: Modifier = Modifier, retry: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = message,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            modifier = Modifier.fillMaxSize(),
            onClick = { retry() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview
@Composable
fun PagingLoadingItemPreview() {
    AppTheme {
        LazyColumn {
            item { LoadingItem() }
        }
    }
}

@Preview
@Composable
fun PagingLoadingErrorItemPreview() {
    AppTheme {
        LazyColumn {
            item { ErrorItem(message = "An error has occurred") {} }
        }
    }
}