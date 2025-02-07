package com.soop.search

import androidx.paging.PagingData
import com.soop.model.GithubRepositoryInfo

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data object EmptyQuery : SearchUiState
    data object LoadFailed : SearchUiState
    data class Success(val data: PagingData<GithubRepositoryInfo>) : SearchUiState
    data object SearchNotReady : SearchUiState
}