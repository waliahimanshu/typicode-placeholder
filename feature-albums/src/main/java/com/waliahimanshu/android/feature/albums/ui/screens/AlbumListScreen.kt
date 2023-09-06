package com.waliahimanshu.android.feature.albums.ui.screens

import android.content.Context
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        observeUIState(context, viewModel)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(stringResource(id = R.string.albums)) }
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AlbumList(viewModel = viewModel)
        }
    }

}

private suspend fun observeUIState(
    context: Context,
    viewModel: AlbumsViewModel
) {

    viewModel.albumsUIState
        .collect { uiStates ->
            when (uiStates) {
                AlbumsUIStates.Error -> makeText(
                    context,
                    context.getString(R.string.generic_error_message),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
}


