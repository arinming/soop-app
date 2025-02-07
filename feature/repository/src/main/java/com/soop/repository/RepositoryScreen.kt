package com.soop.repository

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RepositoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryViewModel = hiltViewModel()
) {
    val repositoryUiState: RepositoryUiState
            by viewModel.repositoryUiState.collectAsStateWithLifecycle()
    RepositoryScreen(
        repositoryUiState = repositoryUiState,
        modifier = modifier,
    )
}

@Composable
internal fun RepositoryScreen(
    repositoryUiState: RepositoryUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        when (repositoryUiState) {
            RepositoryUiState.Loading -> Unit
            RepositoryUiState.Error -> Unit
            is RepositoryUiState.Success -> {
                Text(repositoryUiState.repositoryDetail.repoName)
                Text(repositoryUiState.repositoryDetail.userName)
                Text(repositoryUiState.repositoryDetail.forksCount.toString())
                Text(repositoryUiState.repositoryDetail.stargazersCount.toString())
                Text(repositoryUiState.repositoryDetail.watchersCount.toString())
            }
        }
    }
}