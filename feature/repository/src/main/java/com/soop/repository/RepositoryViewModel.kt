package com.soop.repository

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.soop.domain.GetRepositoryDetailUseCase
import com.soop.domain.GetUserDetailUseCase
import com.soop.domain.GetUserLanguageUseCase
import com.soop.repository.navigation.RepositoryRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    getRepositoryDetailUseCase: GetRepositoryDetailUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
    getUserLanguageUseCase: GetUserLanguageUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val owner = savedStateHandle.toRoute<RepositoryRoute>().owner
    private val repo = savedStateHandle.toRoute<RepositoryRoute>().repo

    private val _showUserInfoBottomSheet = MutableStateFlow(false)
    val showUserInfoBottomSheet: StateFlow<Boolean> = _showUserInfoBottomSheet.asStateFlow()

    val repositoryUiState: StateFlow<RepositoryUiState> = combine(
        getRepositoryDetailUseCase(owner, repo),
        getUserDetailUseCase(owner),
        getUserLanguageUseCase(owner)
    ) { repositoryDetail, userDetail, userLanguage ->
        if (repositoryDetail.repoName.isEmpty() || userDetail.login.isEmpty()) {
            RepositoryUiState.Error
        } else {
            RepositoryUiState.Success(
                repositoryDetail = repositoryDetail,
                userDetail = userDetail,
                userLanguage = userLanguage
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RepositoryUiState.Loading
    )

    fun fetchShowUserInfoBottomSheet(show: Boolean) {
        _showUserInfoBottomSheet.value = show
    }
}
