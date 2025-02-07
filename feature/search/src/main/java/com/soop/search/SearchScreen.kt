package com.soop.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.soop.designsystem.theme.Blue
import com.soop.designsystem.theme.DarkGrey
import com.soop.designsystem.theme.SoopTypography
import com.soop.model.GithubRepositoryInfo

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val githubList = viewModel.githubFlow.collectAsLazyPagingItems()

    SearchScreen(
        modifier = modifier,
        searchQuery = searchQuery,
        searchUiState = searchUiState,
        githubList = githubList,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onSearchTriggered = viewModel::onSearchTriggered,
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchUiState: SearchUiState = SearchUiState.Loading,
    githubList: LazyPagingItems<GithubRepositoryInfo>,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
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
            SearchUiState.Loading,
            SearchUiState.LoadFailed -> Unit

            is SearchUiState.Success -> {
                SearchResultList(repositories = githubList)
            }
        }
    }
}

@Composable
private fun SearchResultList(
    repositories: LazyPagingItems<GithubRepositoryInfo>,
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
                    Text(text = repo.name)
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