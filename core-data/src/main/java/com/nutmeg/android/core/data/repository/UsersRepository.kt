package com.nutmeg.android.core.data.repository

import com.nutmeg.android.core.data.api.JsonPlaceholderService
import com.nutmeg.android.model.User
import javax.inject.Inject

class UsersRepository @Inject constructor(private val placeholderService: JsonPlaceholderService) {
    suspend fun getAllUsers(): List<User> {
        return placeholderService.getUsers().map {
            User(
                id = it.id,
                name = it.name,
            )
        }
    }
}