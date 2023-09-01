package com.waliahimanshu.android.domain.usecase

import com.waliahimanshu.android.core.data.repository.UsersRepository
import com.waliahimanshu.android.model.User
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