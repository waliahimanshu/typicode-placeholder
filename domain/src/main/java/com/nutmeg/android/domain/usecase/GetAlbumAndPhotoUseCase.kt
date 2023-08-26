package com.nutmeg.android.domain.usecase

import com.nutmeg.android.core.data.repository.AlbumsRepository
import com.nutmeg.android.core.data.repository.PhotosRepository
import com.nutmeg.android.model.AlbumDetail
import com.nutmeg.android.model.Photos
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetAlbumAndPhotoUseCase @Inject constructor(
    private val albumRepository: AlbumsRepository,
    private val photosRepository: PhotosRepository
) {

    suspend operator fun invoke(nextPageNumber: Int, limit: Int): List<AlbumDetail> {
        return coroutineScope {
            val albums = albumRepository.getAlbums(nextPageNumber, limit)
            val albumPhotos = albums.map { album ->
                async {
                    photosRepository.getPhotoByAlbumId(album.id)
                }
            }.awaitAll()

            albums.zip(albumPhotos) { album, photo ->
                AlbumDetail(
                    album = album,
                    photos = Photos(
                        photoTitle = photo.title,
                        thumbnailUrl = photo.thumbnailUrl,
                    )
                )
            }
        }
    }
}