package com.soop.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable


@Serializable
data object SearchRoute

@Serializable
data object SearchBaseRoute

fun NavController.navigateToSearch(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(SearchRoute, navOptions)
}

fun NavGraphBuilder.searchSection(
    onRepositoryClick: (String, String) -> Unit,
    repositoryDestination: NavGraphBuilder.() -> Unit
) {
    navigation<SearchBaseRoute>(startDestination = SearchRoute) {
        composable<SearchRoute> {
            SearchScreen(
                onRepositoryClick = onRepositoryClick
            )
        }
        repositoryDestination()
    }
}
