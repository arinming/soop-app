package com.soop.repository

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun RepositoryRoute(
    modifier: Modifier = Modifier,
    viewModel: RepositoryViewModel = hiltViewModel()
) {
    val owner = viewModel.owner
    val repo = viewModel.repo
    RepositoryScreen(
        modifier = modifier,
        owner = owner,
        repo = repo
    )
}

@Composable
internal fun RepositoryScreen(
    modifier: Modifier = Modifier,
    owner: String = "",
    repo: String = ""
) {
    Column(
        modifier = modifier
    ) {
        Text(owner)
        Text(repo)
    }
}