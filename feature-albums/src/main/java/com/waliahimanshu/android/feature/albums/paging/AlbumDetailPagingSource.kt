package com.waliahimanshu.android.feature.albums.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.waliahimanshu.android.domain.usecase.GetAlbumAndPhotoUseCase
import com.waliahimanshu.android.model.AlbumDetail
import javax.inject.Inject

const val STARTING_KEY = 0

class AlbumDetailPagingSource @Inject constructor(
    private val albumAndPhotoUseCase: GetAlbumAndPhotoUseCase
) : PagingSource<Int, AlbumDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumDetail> {
        return try {
            val nextPageNumber = params.key ?: STARTING_KEY
            val loadAlbum = albumAndPhotoUseCase.invoke(
                nextPageNumber = nextPageNumber,
                limit = params.loadSize
            )
            val nextKey = if (loadAlbum.isEmpty()) {
                null
            } else {
                nextPageNumber + params.loadSize
            }

            LoadResult.Page(
                data = loadAlbum,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            // log error
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AlbumDetail>): Int {
        return STARTING_KEY
    }
}