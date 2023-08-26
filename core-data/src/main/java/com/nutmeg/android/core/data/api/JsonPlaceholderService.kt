package com.nutmeg.android.core.data.api

import com.nutmeg.android.core.data.dtos.AlbumDto
import com.nutmeg.android.core.data.dtos.PhotoDto
import com.nutmeg.android.core.data.dtos.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderService {

    @GET("photos")
    suspend fun getPhotoByAlbumId(
        @Query("albumId") albumId: Int,
        @Query("_limit") limit: Int
    ): List<PhotoDto>

    @GET("albums")
    suspend fun getAlbumByPage(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<AlbumDto>

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}


