package com.soop.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soop.data.repository.GithubRepository
import com.soop.model.GithubRepositoryInfo
import com.soop.model.RepositoryDetail
import com.soop.model.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    val searchUiState: StateFlow<SearchUiState> =
        githubRepository.getGithub(searchQuery.value)
            .map {
                SearchUiState.Success
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchUiState.Loading
            )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    private val _githubFlow: MutableStateFlow<Flow<PagingData<GithubRepositoryInfo>>> =
        MutableStateFlow(emptyFlow())

    @OptIn(ExperimentalCoroutinesApi::class)
    val githubFlow: Flow<PagingData<GithubRepositoryInfo>> =
        _githubFlow.flatMapLatest { it }

    private val _repositoryFlow: MutableStateFlow<Flow<RepositoryDetail>> =
        MutableStateFlow(emptyFlow())
    val repositoryFlow: Flow<RepositoryDetail> =
        _repositoryFlow.flatMapLatest { it }

    private val _userFlow: MutableStateFlow<Flow<UserDetail>> =
        MutableStateFlow(emptyFlow())
    val userFlow: Flow<UserDetail> =
        _userFlow.flatMapLatest { it }

    fun onSearchTriggered(query: String) {
        if (query.isBlank()) return
        _githubFlow.value = githubRepository.getGithub(query).cachedIn(viewModelScope)
    }

    fun getRepositoryDetail() {
        _repositoryFlow.value = githubRepository.getRepositoryDetail(
            owner = "octocat",
            repo = "Hello-World"
        )
    }

    fun getUserDetail() {
        _userFlow.value = githubRepository.getUserDetail(
            username = "arinming"
        )
    }
}

private const val SEARCH_QUERY = "searchQuery"