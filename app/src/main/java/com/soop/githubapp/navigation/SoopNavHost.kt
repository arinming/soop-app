package com.soop.githubapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.soop.githubapp.ui.SoopAppState
import com.soop.repository.navigation.navigateToRepository
import com.soop.repository.navigation.repositoryScreen
import com.soop.search.SearchBaseRoute
import com.soop.search.searchSection

@Composable
fun SoopNavHost(
    appState: SoopAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = SearchBaseRoute,
        modifier = modifier
    ) {
        searchSection(
            onRepositoryClick = { owner, repo ->
                navController.navigateToRepository(owner = owner, repo = repo)
            }
        ) {
            repositoryScreen()
        }
    }
}