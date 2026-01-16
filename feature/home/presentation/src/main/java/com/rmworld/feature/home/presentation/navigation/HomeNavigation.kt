package com.rmworld.feature.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.rmworld.feature.home.presentation.HomeRoute
import extensions.composableWithCompositionLocal
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen() = navigate(route = HomeRoute)

fun NavGraphBuilder.homeScreen(
    onCharacterClicked: (Int) -> Unit
) {

    composableWithCompositionLocal<HomeRoute> { backStackEntry ->
        HomeRoute(backStackEntry, onCharacterClicked)
    }
}