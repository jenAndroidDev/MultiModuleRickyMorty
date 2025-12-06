package com.feature.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.home.presentation.HomeRoute
import com.feature.home.presentation.HomeScreen

const val HOME_ROUTE  = "home_route"
fun NavController.navigateToHomeScreen() = navigate(HOME_ROUTE)

fun NavGraphBuilder.homeScreen(
    onCharacterClicked: (Int) -> Unit
) {
    composable(HOME_ROUTE) {it->
        HomeRoute(it){onCharacterClicked.invoke(id)}
    }
}