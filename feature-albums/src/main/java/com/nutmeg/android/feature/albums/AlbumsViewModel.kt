package com.nutmeg.android.feature.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nutmeg.android.domain.usecase.GetUserInfoUseCase
import com.nutmeg.android.feature.albums.paging.AlbumDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    albumDetailsUseCase: AlbumDetailsUseCase,
    userInfoUseCase: GetUserInfoUseCase,
    private val uiModelMapper: AlbumDetailUIModelMapper,
) : ViewModel() {

    private val _albumsUIState = MutableSharedFlow<AlbumsUIStates>()
    val albumsUIState = _albumsUIState.asSharedFlow()

    val albumItems =
        albumDetailsUseCase.getAlbumDetails()
            .combine(userInfoUseCase.getUsers()) { pageData, users ->
                uiModelMapper.map(pageData, users)
            }
            .catch {
                // log exception
                _albumsUIState.emit(AlbumsUIStates.Error)
            }
            .cachedIn(viewModelScope)

}

sealed interface AlbumsUIStates {
    object Error : AlbumsUIStates
}