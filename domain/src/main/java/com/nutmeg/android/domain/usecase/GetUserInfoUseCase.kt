package com.nutmeg.android.domain.usecase

import com.nutmeg.android.core.data.repository.UsersRepository
import com.nutmeg.android.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UsersRepository,
) {
    fun getUsers(): Flow<List<User>> {
        return flow {
            emit(userRepository.getAllUsers())
        }
    }
}