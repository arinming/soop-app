package com.soop.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soop.designsystem.NetworkImage
import com.soop.designsystem.R.string
import com.soop.designsystem.SoopBottomSheet
import com.soop.designsystem.SoopButton
import com.soop.designsystem.theme.Black
import com.soop.designsystem.theme.DarkBlue
import com.soop.designsystem.theme.GreyDark
import com.soop.designsystem.theme.SoopTypography
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
            .padding(16.dp)
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
                                language = repositoryUiState.userLanguage,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NetworkImage(
                imageUrl = repositoryDetail.avatarUrl,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = repositoryDetail.userName,
                style = SoopTypography.headlineSmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            SoopButton(
                onClick = onMoreClick,
                text = { Text(stringResource(string.feature_repository_user_more_button)) },
                modifier = Modifier
                    .wrapContentWidth()
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = repositoryDetail.repoName,
            style = SoopTypography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (!repositoryDetail.description.isNullOrBlank()) {
            Text(
                text = "${repositoryDetail.description}",
                style = SoopTypography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatItem(
                icon = Icons.Default.Star,
                value = repositoryDetail.stargazersCount,
                label = stringResource(string.feature_repository_user_stars)
            )
            StatItem(
                icon = Icons.Default.Visibility,
                value = repositoryDetail.watchersCount,
                label = stringResource(string.feature_repository_user_watchers)
            )
            StatItem(
                icon = Icons.AutoMirrored.Filled.Send,
                value = repositoryDetail.forksCount,
                label = stringResource(string.feature_repository_user_forks)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}


@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Black,
            modifier = Modifier.width(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value $label",
            style = SoopTypography.bodySmall
        )
    }
}

@Composable
fun UserInfoBottomSheet(
    language: String = "",
    userDetail: UserDetail
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                imageUrl = userDetail.avatarUrl,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = userDetail.login,
                style = SoopTypography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FollowInfo(
                label = stringResource(string.feature_repository_user_followers),
                count = userDetail.followers
            )
            FollowInfo(
                label = stringResource(string.feature_repository_user_following),
                count = userDetail.following
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (language.isNotBlank()) {
            Column {
                Text(
                    text = stringResource(string.feature_repository_user_language),
                    style = SoopTypography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LanguageChip(language)
            }
        }
    }
}


@Composable
fun FollowInfo(label: String, count: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "$count",
            style = SoopTypography.labelMedium,
            color = DarkBlue
        )
        Text(
            text = label,
            style = SoopTypography.labelSmall,
            color = GreyDark
        )
    }
}

@Composable
fun LanguageChip(language: String) {
    Box(
        modifier = Modifier
            .background(LightGray, shape = RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = language,
            style = SoopTypography.labelMedium,
            color = DarkBlue
        )
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