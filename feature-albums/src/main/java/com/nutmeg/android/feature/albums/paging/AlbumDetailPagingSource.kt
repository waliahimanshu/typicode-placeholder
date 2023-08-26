package com.nutmeg.android.feature.albums.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nutmeg.android.domain.usecase.GetAlbumAndPhotoUseCase
import com.nutmeg.android.model.AlbumDetail

const val STARTING_KEY = 0

class AlbumDetailPagingSource(
    private val albumAndPhotoUseCase: GetAlbumAndPhotoUseCase
) : PagingSource<Int, AlbumDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumDetail> {
        return try {
            val nextPageNumber = params.key ?: STARTING_KEY
            val loadAlbum = albumAndPhotoUseCase.invoke(nextPageNumber, params.loadSize)
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