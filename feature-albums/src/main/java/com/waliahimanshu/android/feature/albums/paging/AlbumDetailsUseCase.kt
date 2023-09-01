package com.waliahimanshu.android.feature.albums.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.waliahimanshu.android.model.AlbumDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 5

class AlbumDetailsUseCase @Inject constructor(private val pagingSource: AlbumDetailPagingSource) {

    fun getAlbumDetails(): Flow<PagingData<AlbumDetail>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}