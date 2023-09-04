package com.waliahimanshu.android.feature.albums.ui.screens

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.waliahimanshu.android.feature.albums.AlbumsUIStates
import com.waliahimanshu.android.feature.albums.AlbumsViewModel
import com.waliahimanshu.android.feature.albums.ui.AlbumList
import com.waliahimanshu.android.feature.photos.R


@Composable
fun AlbumListScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        AlbumList(viewModel)
        AlbumUIState(viewModel)
    }
}

@Composable
private fun AlbumUIState(viewModel: AlbumsViewModel) {
    val context = LocalContext.current
    val message = stringResource(id = R.string.generic_error_message)

    LaunchedEffect(Unit) {
        viewModel.albumsUIState
            .collect { uiStates ->
                when (uiStates) {
                    AlbumsUIStates.Error -> makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}


