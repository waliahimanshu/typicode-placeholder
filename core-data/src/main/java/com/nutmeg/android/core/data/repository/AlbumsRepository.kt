package com.nutmeg.android.core.data.repository

import com.nutmeg.android.core.data.api.JsonPlaceholderService
import com.nutmeg.android.model.Album
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val placeholderService: JsonPlaceholderService) {

    suspend fun getAlbums(start: Int, limit: Int): List<Album> {
        return placeholderService.getAlbumByPage(
            start = start,
            limit = limit
        ).map { albumDto ->
            with(albumDto) {
                Album(
                    id = id,
                    userId = userId,
                    title = title,
                )
            }
        }
    }
}