package com.soop.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soop.data.repository.GithubRepository
import com.soop.model.GithubRepositoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _githubFlow: MutableStateFlow<Flow<PagingData<GithubRepositoryInfo>>> =
        MutableStateFlow(emptyFlow())

    @OptIn(ExperimentalCoroutinesApi::class)
    val githubFlow: Flow<PagingData<GithubRepositoryInfo>> =
        _githubFlow.flatMapLatest { it }

    fun getGithubData() {
        refreshGithubFLow()
    }

    private fun refreshGithubFLow() {
        _githubFlow.value = githubRepository.getGithub(
            ""
        ).cachedIn(viewModelScope)
    }
}