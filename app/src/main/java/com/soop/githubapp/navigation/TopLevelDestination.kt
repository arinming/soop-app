package com.soop.githubapp.navigation

import androidx.annotation.StringRes
import com.soop.designsystem.R.string
import com.soop.repository.navigation.RepositoryRoute
import com.soop.search.SearchRoute
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