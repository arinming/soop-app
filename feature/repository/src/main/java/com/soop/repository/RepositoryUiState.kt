package com.soop.repository

import com.soop.model.RepositoryDetail

sealed interface RepositoryUiState {
    data object Loading : RepositoryUiState
    data object Error : RepositoryUiState
    data class Success(
        val repositoryDetail: RepositoryDetail
    ) : RepositoryUiState
}