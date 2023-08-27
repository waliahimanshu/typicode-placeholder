package com.nutmeg.android.feature.albums.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nutmeg.android.model.AlbumDetail
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 5

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor (private val albumDetailPagingSource: AlbumDetailPagingSource) {

    fun getAlbumDetails(): Flow<PagingData<AlbumDetail>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { albumDetailPagingSource }
        ).flow
    }
}