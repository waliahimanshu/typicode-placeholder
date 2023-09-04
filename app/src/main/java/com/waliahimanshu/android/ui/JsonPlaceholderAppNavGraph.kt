package com.waliahimanshu.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.waliahimanshu.android.feature.albums.ui.screens.AlbumListScreen
import com.waliahimanshu.android.feature.albums.ui.screens.Routes

@Composable
fun JsonPlaceholderAppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ALBUM_LIST_SCREEN) {
        composable(Routes.ALBUM_LIST_SCREEN) {
            AlbumListScreen()
        }
    }
}