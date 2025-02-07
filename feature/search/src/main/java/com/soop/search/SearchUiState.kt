package com.soop.search

import androidx.paging.PagingData
import com.soop.model.GithubRepositoryInfo
import kotlinx.coroutines.flow.Flow

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data object Error : SearchUiState
    data object Empty : SearchUiState
    data class Success(
        val githubRepositories: Flow<PagingData<GithubRepositoryInfo>>
    ) : SearchUiState
}