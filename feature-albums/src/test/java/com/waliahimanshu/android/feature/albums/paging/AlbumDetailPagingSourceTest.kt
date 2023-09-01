package com.waliahimanshu.android.feature.albums.paging

import androidx.paging.PagingSource
import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.domain.usecase.GetAlbumAndPhotoUseCase
import com.waliahimanshu.android.model.AlbumDetail
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AlbumDetailPagingSourceTest {

    private val mockUseCase: GetAlbumAndPhotoUseCase = mock()
    private val fixture = KFixture()

    private val params = PagingSource.LoadParams.Append(
        key = 1,
        loadSize = 5,
        placeholdersEnabled = true
    )

    @Test
    fun `When PagingSource load is called Then use-case is called with valid start end offsets`() =
        runTest {
            val albumDetailPagingSource = AlbumDetailPagingSource(mockUseCase)

            albumDetailPagingSource.load(params)

            verify(mockUseCase).invoke(params.key, params.loadSize)
        }

    @Test
    fun `When PagingSource load is called Then use-case returns expected data`() =
        runTest {
            val albumDetail = List(3) {
                fixture<AlbumDetail>()
            }
            whenever(mockUseCase.invoke(params.key, params.loadSize)).thenReturn(albumDetail)
            val expectedResult = PagingSource.LoadResult.Page(
                data = albumDetail,
                prevKey = null,
                nextKey = params.key + params.loadSize
            )
            val pagingSource = AlbumDetailPagingSource(mockUseCase)

            val loadResult = pagingSource.load(params)

            assertEquals(loadResult, expectedResult)
        }

    @Test
    fun `When PagingSource load is called When error occurs Then use-case returns expected data`() =
        runTest {
            val exception = RuntimeException()
            whenever(mockUseCase.invoke(params.key, params.loadSize)).thenThrow(exception)
            val expectedResult = PagingSource.LoadResult.Error<Int, AlbumDetail>(
                throwable = exception
            )
            val pagingSource = AlbumDetailPagingSource(mockUseCase)
            val loadResult = pagingSource.load(params)

            assertEquals(loadResult, expectedResult)
        }
}