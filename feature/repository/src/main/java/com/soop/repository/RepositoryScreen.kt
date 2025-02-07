package com.soop.repository

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soop.designsystem.R.string
import com.soop.designsystem.SoopBottomSheet
import com.soop.designsystem.SoopButton
import com.soop.model.RepositoryDetail
import com.soop.model.UserDetail

@Composable
fun RepositoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryViewModel = hiltViewModel()
) {
    val repositoryUiState: RepositoryUiState
            by viewModel.repositoryUiState.collectAsStateWithLifecycle()
    val showUserInfoBottomSheet: Boolean by viewModel.showUserInfoBottomSheet.collectAsStateWithLifecycle()

    RepositoryScreen(
        repositoryUiState = repositoryUiState,
        showUserInfoBottomSheet = showUserInfoBottomSheet,
        onDismissRequest = { viewModel.fetchShowUserInfoBottomSheet(false) },
        onMoreClick = { viewModel.fetchShowUserInfoBottomSheet(true) },
        modifier = modifier,
    )
}

@Composable
internal fun RepositoryScreen(
    repositoryUiState: RepositoryUiState,
    showUserInfoBottomSheet: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onMoreClick: () -> Unit,
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
                RepositoryDetail(
                    repositoryDetail = repositoryUiState.repositoryDetail,
                    onMoreClick = onMoreClick
                )
                if (showUserInfoBottomSheet) {
                    SoopBottomSheet(
                        onDismissRequest = onDismissRequest,
                        content = {
                            UserInfoBottomSheet(
                                userDetail = repositoryUiState.userDetail
                            )
                        },
                    )
                }
            }
        }
    }


}

@Composable
fun RepositoryDetail(
    repositoryDetail: RepositoryDetail,
    onMoreClick: () -> Unit = {}
) {
    Column {
        Text(repositoryDetail.repoName)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(repositoryDetail.userName)
            SoopButton(
                onClick = onMoreClick,
                text = { Text(stringResource(string.feature_repository_user_more_button)) }
            )
        }
        Text(repositoryDetail.forksCount.toString())
        Text(repositoryDetail.stargazersCount.toString())
        Text(repositoryDetail.watchersCount.toString())
    }
}

@Composable
fun UserInfoBottomSheet(
    userDetail: UserDetail
) {
    Column {
        Text("${userDetail.followers}")
    }
}

@Preview
@Composable
fun RepositoryDetailPreview() {
    RepositoryDetail(
        repositoryDetail = RepositoryDetail(
            repoName = "repoName",
            userName = "userName",
            forksCount = 1,
            stargazersCount = 1,
            watchersCount = 1,
            description = "description",
            avatarUrl = ""
        )
    )
}