package com.soop.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getGithubData(
            query = "android"
        )
    }
    val githubList = viewModel.githubFlow.collectAsLazyPagingItems()

    SearchScreen(

    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

    }
}