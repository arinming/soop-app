package com.soop.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.soop.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onSearchTriggered(query: String) {
        if (query.isBlank()) return

        _searchUiState.value = SearchUiState.Loading

        viewModelScope.launch {
            githubRepository.getGithub(query)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _searchUiState.value = SearchUiState.Success(
                        githubRepositories = flow { emit(pagingData) }
                    )
                }
        }
    }
}

private const val SEARCH_QUERY = "searchQuery"
