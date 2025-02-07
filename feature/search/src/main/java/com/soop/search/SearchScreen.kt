package com.soop.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soop.designsystem.theme.Blue
import com.soop.designsystem.theme.DarkGrey
import com.soop.designsystem.theme.SoopTypography

@Composable
internal fun SearchRoute(
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
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchUiState: SearchUiState = SearchUiState.Loading,
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

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}