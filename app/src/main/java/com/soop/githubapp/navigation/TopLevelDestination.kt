package com.soop.githubapp.navigation

import com.soop.repository.navigation.RepositoryRoute
import kotlin.reflect.KClass


enum class TopLevelDestination(
    val route: KClass<*>,
    val isNavigationIcon: Boolean = false
) {
    REPOSITORY(
        route = RepositoryRoute::class,
        isNavigationIcon = true
    ),
}