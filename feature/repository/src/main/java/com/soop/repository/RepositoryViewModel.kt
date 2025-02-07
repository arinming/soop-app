package com.soop.repository

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.soop.data.repository.GithubRepository
import com.soop.repository.navigation.RepositoryRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    githubRepository: GithubRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val owner = savedStateHandle.toRoute<RepositoryRoute>().owner
    private val repo = savedStateHandle.toRoute<RepositoryRoute>().repo

    private val _showUserInfoBottomSheet = MutableStateFlow(false)
    val showUserInfoBottomSheet: StateFlow<Boolean> = _showUserInfoBottomSheet.asStateFlow()

    val repositoryUiState: StateFlow<RepositoryUiState> = githubRepository.getRepositoryDetail(
        owner = owner,
        repo = repo
    ).map {
        RepositoryUiState.Success(
            repositoryDetail = it
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RepositoryUiState.Loading
        )

    fun fetchShowUserInfoBottomSheet(show: Boolean) {
        _showUserInfoBottomSheet.value = show
    }
}