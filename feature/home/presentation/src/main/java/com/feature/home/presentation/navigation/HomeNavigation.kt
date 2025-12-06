package com.feature.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.home.presentation.HomeRoute
import kotlinx.serialization.Serializable

@Serializable data object HomeRoute

fun NavController.navigateToHomeScreen() = navigate(route = HomeRoute)

fun NavGraphBuilder.homeScreen(
    onCharacterClicked: (Int) -> Unit
) {
    composable<HomeRoute> { it->
        HomeRoute(it){onCharacterClicked.invoke(id)}
    }
}