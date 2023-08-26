package com.nutmeg.android.core.data.repository

import com.nutmeg.android.core.data.api.JsonPlaceholderService
import com.nutmeg.android.model.AlbumPhotos
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val placeholderService: JsonPlaceholderService) {

    suspend fun getPhotoByAlbumId(albumId: Int): AlbumPhotos {
        val limit = 1 // get only first photo of each item
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