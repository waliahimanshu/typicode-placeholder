package com.waliahimanshu.android.core.data.repository

import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.core.data.api.JsonPlaceholderService
import com.waliahimanshu.android.core.data.dtos.UserDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class UsersRepositoryTest {

    private val mockService: JsonPlaceholderService = mock()

    private val fixture = KFixture()

    @Test
    fun `When getAllUsers is called Then user dto is mapped to domain as expected `() = runTest {
        val userDtos = List(3) {
            fixture<UserDto>()
        }

        whenever(mockService.getUsers()).thenReturn(userDtos)
        val usersRepository = UsersRepository(mockService)

        val userList = usersRepository.getAllUsers()

        userDtos.forEachIndexed { index, userDto ->
            assertEquals(userList[index].name, userDto.name)
            assertEquals(userList[index].id, userDto.id)
        }
    }
}