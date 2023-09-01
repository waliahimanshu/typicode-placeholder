package com.waliahimanshu.android.domain.usecase

import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.core.data.repository.AlbumsRepository
import com.waliahimanshu.android.core.data.repository.PhotosRepository
import com.waliahimanshu.android.model.Album
import com.waliahimanshu.android.model.AlbumPhotos
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
internal class GetAlbumAndPhotoUseCaseTest {

    private val mockAlbumRepository: AlbumsRepository = mock()
    private val mockPhotosRepository: PhotosRepository = mock()
    private val fixture = KFixture()

    @Test
    fun `When album & photo repo are called successfully Then map result to album detail`() = runTest {
        val start = 1
        val limit = 5
        val albums = List(3) {
            fixture<Album>()
        }
        val photos = fixture<AlbumPhotos>()

        whenever(mockAlbumRepository.getAlbums(start, limit)).thenReturn(albums)
        albums.map { album ->
            whenever(mockPhotosRepository.getPhotoByAlbumId(album.id)).thenReturn(photos)
        }

        val useCase = GetAlbumAndPhotoUseCase(
            mockAlbumRepository,
            mockPhotosRepository,
        )

        val albumDetail = useCase.invoke(start, limit)
        albums.forEachIndexed { index, album ->
            assertEquals(albumDetail[index].album, album)
            assertEquals(albumDetail[index].photos.photoTitle, photos.title)
            assertEquals(albumDetail[index].photos.thumbnailUrl, photos.thumbnailUrl)
        }
    }
}