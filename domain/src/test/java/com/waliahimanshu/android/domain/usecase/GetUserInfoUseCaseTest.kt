package com.waliahimanshu.android.domain.usecase

import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.core.data.repository.UsersRepository
import com.waliahimanshu.android.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserInfoUseCaseTest {

    private val mockUserRepo: UsersRepository = mock()

    private val fixture = KFixture()

    @Test
    fun `When getAllUsers is called Then map dto to user domain`() = runTest {
        val userList = List(3) {
            fixture<User>()
        }
        whenever(mockUserRepo.getAllUsers()).thenReturn(userList)
        val useCase = GetUserInfoUseCase(mockUserRepo)

        val users = useCase.getUsers().first()

        userList.forEachIndexed { index, user ->
            assertEquals(users[index].id, user.id)
            assertEquals(users[index].name, user.name)
        }
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun `When getAllUsers fails Then throw exception`() = runTest {
        whenever(mockUserRepo.getAllUsers()).thenThrow(RuntimeException())

        val useCase = GetUserInfoUseCase(mockUserRepo)

        useCase.getUsers().first()
    }
}