package com.mmd.feature.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mmd.feature.presentation.DetailRoute
import kotlinx.serialization.Serializable


@Serializable data object DetailRoute

fun NavController.navigateToDetailScreen() = navigate(route = DetailRoute)

fun NavGraphBuilder.detailScreen(){
    composable<DetailRoute> {
        DetailRoute()
    }
}

