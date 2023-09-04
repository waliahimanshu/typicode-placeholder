package com.waliahimanshu.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.waliahimanshu.android.feature.albums.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                JsonPlaceHolderApp()
            }
        }
    }

    @Composable
    fun JsonPlaceHolderApp() = JsonPlaceholderAppNavGraph()
}

