package com.soop.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.soop.designsystem.NetworkImage
import com.soop.designsystem.theme.Blue
import com.soop.designsystem.theme.DarkGrey
import com.soop.designsystem.theme.SoopTypography
import com.soop.designsystem.theme.languageColors
import com.soop.model.GithubRepositoryInfo

@Composable
fun SearchScreen(
    onRepositoryClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreen(
        modifier = modifier,
        searchQuery = searchQuery,
        searchUiState = searchUiState,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchTriggered = viewModel::onSearchTriggered,
        onRepositoryClick = onRepositoryClick
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchUiState: SearchUiState = SearchUiState.Empty,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    onRepositoryClick: (String, String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
        )
        when (searchUiState) {
            SearchUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            SearchUiState.Error -> Unit
            SearchUiState.Empty -> Unit

            is SearchUiState.Success -> {
                val lazyPagingItems = searchUiState.githubRepositories.collectAsLazyPagingItems()
                SearchResultList(
                    repositories = lazyPagingItems,
                    onRepositoryClick = onRepositoryClick
                )
            }

        }
    }
}


@Composable
private fun SearchResultList(
    repositories: LazyPagingItems<GithubRepositoryInfo>,
    onRepositoryClick: (String, String) -> Unit
) {
    val state = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize(),
            state = state
        ) {
            items(repositories.itemCount) { index ->
                val repo = repositories[index]
                if (repo != null) {
                    RepositoryItem(
                        repository = repo,
                        onClick = { onRepositoryClick(repo.username, repo.name) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RepositoryItem(
    repository: GithubRepositoryInfo,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                imageUrl = repository.avatarUrl,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = repository.fullName,
                    style = SoopTypography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = repository.description ?: "",
                    style = SoopTypography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = DarkGrey
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = repository.stars.toString(),
                        style = SoopTypography.bodyMedium
                    )
                }
                repository.language?.let { language ->
                    languageColors[language]?.let { color ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Circle,
                                contentDescription = null,
                                tint = color,
                                modifier = Modifier.padding(4.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = language,
                                style = SoopTypography.bodyMedium,
                                color = DarkGrey
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun SearchToolbar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        SearchTextField(
            value = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
        )
    }
}

@Composable
fun SearchTextField(
    value: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Search,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(value)
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onSearchQueryChanged(it)
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isBlank()) return@KeyboardActions
                onSearchExplicitlyTriggered()
            },
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .background(White),
        textStyle = SoopTypography.bodyLarge.copy(color = DarkGrey),
        colors = TextFieldDefaults.colors(
            focusedTextColor = DarkGrey,
            unfocusedTextColor = DarkGrey,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedIndicatorColor = Blue,
            focusedLabelColor = Blue,
            cursorColor = Blue,
            unfocusedIndicatorColor = DarkGrey,
        ),
    )
}
