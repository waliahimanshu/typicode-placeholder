package com.waliahimanshu.android.core.data.repository

import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.core.data.api.JsonPlaceholderService
import com.waliahimanshu.android.core.data.dtos.PhotoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class PhotosRepositoryTest {

    private val mockService: JsonPlaceholderService = mock()
    private val fixture = KFixture()

    @Test
    fun `When getPhotoByAlbumId is called Then map first photo DTO to domain`() = runTest {
        val albumId = 123

        val photoDtoList = List(3) {
            fixture<PhotoDto>()
        }
        whenever(mockService.getPhotoByAlbumId(albumId, 1)).thenReturn(photoDtoList)

        val repository = PhotosRepository(mockService)
        val expected = repository.getPhotoByAlbumId(albumId)

        val first = photoDtoList.first()
        assertEquals(expected.albumId, first.albumId)
        assertEquals(expected.title, first.title)
        assertEquals(expected.id, first.id)
        assertEquals(expected.thumbnailUrl, first.thumbnailUrl)
    }

}