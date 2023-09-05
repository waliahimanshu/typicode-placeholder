package com.waliahimanshu.android.feature.albums

import androidx.paging.PagingData
import com.flextrade.kfixture.KFixture
import com.waliahimanshu.android.domain.usecase.GetUserInfoUseCase
import com.waliahimanshu.android.feature.albums.model.AlbumDetailUIModel
import com.waliahimanshu.android.feature.albums.paging.AlbumDetailsUseCase
import com.waliahimanshu.android.model.AlbumDetail
import com.waliahimanshu.android.model.User
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsViewModelTest {

    private val mockUiModelMapper: AlbumDetailUIModelMapper = mock()
    private val mockUserUseCase: GetUserInfoUseCase = mock()
    private val mockAlbumDetailUseCase: AlbumDetailsUseCase = mock()
    private val fixture = KFixture()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given pagedData and userList flow emits Then transform the data to paged data UI model`() =
        runTest {
            val pagedData = PagingData.from(List(4) {
                fixture<AlbumDetail>()
            })
            val uiModelList = List(4) {
                fixture<AlbumDetailUIModel>()
            }
            val pagedUiModel = PagingData.from(uiModelList)
            val users = List(4) {
                fixture<User>()
            }

            whenever(mockAlbumDetailUseCase.getAlbumDetails()).thenReturn(flowOf(pagedData))
            whenever(mockUserUseCase.getUsers()).thenReturn(flowOf(users))
            whenever(mockUiModelMapper.map(pagedData, users)).thenReturn(pagedUiModel)

            val viewModel = AlbumsViewModel(
                albumDetailsUseCase = mockAlbumDetailUseCase,
                userInfoUseCase = mockUserUseCase,
                uiModelMapper = mockUiModelMapper
            )

            advanceUntilIdle()
            val expectedItem = viewModel.albumItems.first()
            assertNotNull(expectedItem)
        }
}
