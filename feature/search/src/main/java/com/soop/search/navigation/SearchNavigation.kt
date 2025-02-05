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
    navigate(
        route = SearchRoute
    ) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
        navOptions()
    }
}

fun NavGraphBuilder.searchSection(
) {
    navigation<SearchBaseRoute>(startDestination = SearchRoute) {
        composable<SearchRoute> {
            SearchRoute()
        }
    }
}
