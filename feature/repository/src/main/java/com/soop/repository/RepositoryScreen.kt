package com.soop.repository

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
            RepositoryUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            RepositoryUiState.Error -> {
                Icons.Rounded.Error
            }
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