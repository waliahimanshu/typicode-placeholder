package com.waliahimanshu.android.feature.albums.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.waliahimanshu.android.feature.albums.AlbumsViewModel
import com.waliahimanshu.android.feature.albums.ui.theme.AlbumList

@Composable
fun AlbumListScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        AlbumList(viewModel)
    }

}