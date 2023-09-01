package com.waliahimanshu.android.feature.albums

import androidx.paging.PagingData
import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.model.AlbumDetail
import com.waliahimanshu.android.model.User
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

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailUIModelMapperTest {
    private val testDispatcher = StandardTestDispatcher()

    private val fixture = KFixture()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `Given pagedData and user When map called Then transform to UIMModel`() = runTest{
        val uiModelMapper = AlbumDetailUIModelMapper()
        val data = List(4) {
            fixture<AlbumDetail>()
        }
        val pagedData = PagingData.from(data)
        val users = List(4) {
            fixture<User>().copy(id = data.first().album.userId)
        }

        val expected = uiModelMapper.map(pagedData, users)
        val expectedPagedList = getDataFromPagedData(expected)

        assertEquals(expectedPagedList.size, data.size)
        data.forEachIndexed { index, uiModel ->
            assertEquals(expectedPagedList[index].albumId, uiModel.album.id)
            assertEquals(expectedPagedList[index].albumTitle, uiModel.album.title)
            assertEquals(expectedPagedList[index].photoTitle, uiModel.photos.photoTitle)
            assertEquals(expectedPagedList[index].thumbnailUrl, uiModel.photos.thumbnailUrl)
        }
        assertEquals(expectedPagedList.first().userName, users.first().name)
    }
}