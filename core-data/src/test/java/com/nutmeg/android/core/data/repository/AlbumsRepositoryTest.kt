package com.nutmeg.android.core.data.repository

import com.flextrade.kfixture.KFixture
import com.nutmeg.android.core.data.api.JsonPlaceholderService
import com.nutmeg.android.core.data.dtos.AlbumDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class AlbumsRepositoryTest {

    private val mockService: JsonPlaceholderService = mock()
    private val fixture = KFixture()

    @Test
    fun `When getAlbums is called Then album DTO is mapped to domain as expected `() = runTest {
        val dtoList = List(3) { fixture<AlbumDto>() }
        val start = 1
        val limit = 5

        whenever(mockService.getAlbumByPage(start, limit)).thenReturn(dtoList)
        val albumsRepository = AlbumsRepository(mockService)

        val albums = albumsRepository.getAlbums(start, limit)

        dtoList.forEachIndexed { index, albumDto ->
            assertEquals(albums[index].id, albumDto.id)
            assertEquals(albums[index].userId, albumDto.userId)
            assertEquals(albums[index].title, albumDto.title)
        }
    }
}