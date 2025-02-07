package com.soop.repository

import com.soop.model.RepositoryDetail
import com.soop.model.UserDetail

sealed interface RepositoryUiState {
    data object Loading : RepositoryUiState
    data object Error : RepositoryUiState
    data class Success(
        val repositoryDetail: RepositoryDetail,
        val userDetail: UserDetail
    ) : RepositoryUiState
}