package com.nutmeg.android.core.data.repository

import com.flextrade.kfixture.KFixture
import com.nutmeg.android.core.data.api.JsonPlaceholderService
import com.nutmeg.android.core.data.dtos.UserDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UsersRepositoryTest {

    private val mockService: JsonPlaceholderService = mock()

    private val fixture = KFixture()
    fun setUp() {
    }

    fun tearDown() {
    }

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