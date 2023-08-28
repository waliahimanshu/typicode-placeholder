package com.nutmeg.android.feature.albums

import androidx.paging.PagingData
import androidx.paging.map
import com.nutmeg.android.feature.albums.model.AlbumDetailUIModel
import com.nutmeg.android.model.AlbumDetail
import com.nutmeg.android.model.User
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class AlbumDetailUIModelMapper @Inject constructor() {

    fun map(
        pagingData: PagingData<AlbumDetail>,
        users: List<User>
    ): PagingData<AlbumDetailUIModel> {

        return pagingData.map { detail ->
            val album = detail.album
            AlbumDetailUIModel(
                albumId = album.id,
                albumTitle = album.title,
                userName = users.find { album.userId == it.id }?.name ?: "Unknown",
                photoTitle = detail.photos.photoTitle,
                thumbnailUrl = detail.photos.thumbnailUrl
            )
        }
    }
}
