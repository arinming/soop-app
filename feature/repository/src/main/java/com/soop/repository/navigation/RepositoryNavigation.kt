package com.soop.repository.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.soop.repository.RepositoryRoute
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryRoute(
    val owner: String,
    val repo: String,
)

fun NavController.navigateToRepository(
    owner: String,
    repo: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = RepositoryRoute(owner = owner, repo = repo)) {
        navOptions()
    }
}

fun NavGraphBuilder.repositoryScreen() {
    composable<RepositoryRoute> {
        RepositoryRoute()
    }
}