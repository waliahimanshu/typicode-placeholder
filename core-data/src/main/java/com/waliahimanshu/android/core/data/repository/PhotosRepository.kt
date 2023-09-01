package com.waliahimanshu.android.core.data.repository

import com.waliahimanshu.android.core.data.api.JsonPlaceholderService
import com.waliahimanshu.android.model.AlbumPhotos
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val placeholderService: JsonPlaceholderService) {

    suspend fun getPhotoByAlbumId(albumId: Int): AlbumPhotos {
        val limit = 1 // get only first photo of each item using api limit offset
        return placeholderService.getPhotoByAlbumId(albumId = albumId, limit = limit).map { dto ->
            with(dto) {
                AlbumPhotos(
                    id = id,
                    albumId = dto.albumId,
                    thumbnailUrl = thumbnailUrl,
                    title = title,
                )
            }
        }.first()
    }
}