package com.waliahimanshu.android.model

data class AlbumDetail(
    val album: Album,
    val photos: Photos
)

data class Photos(
    val thumbnailUrl: String,
    val photoTitle: String
)