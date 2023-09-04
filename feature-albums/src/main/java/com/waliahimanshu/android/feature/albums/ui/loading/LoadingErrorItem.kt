package com.waliahimanshu.android.feature.albums.ui.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import com.waliahimanshu.android.feature.photos.R

@Composable
fun LoadingItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
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
fun ErrorItem(message: String, retry: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(68.dp),
                painter = painterResource(id = R.drawable.baseline_error_24),
                contentDescription = stringResource(R.string.generic_error_message),
            )
            Text(
                text = message,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
            )


            Button(modifier = Modifier
                .padding(start = 8.dp)
                .align(CenterVertically), onClick = { retry() }) {
                Text(text = stringResource(id = R.string.retry))
            }
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
            item { ErrorItem("An error has occurred") {} }
        }
    }
}