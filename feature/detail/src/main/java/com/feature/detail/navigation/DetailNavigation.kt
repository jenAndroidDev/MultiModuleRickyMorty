package com.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.detail.DetailRoute

const val DETAIL_ROUTE = "detail_route"

fun NavController.navigateToDetailScreen() = navigate(DETAIL_ROUTE)

fun NavGraphBuilder.detailScreen(){
    composable(
        route = DETAIL_ROUTE
    ) {
        DetailRoute()
    }
}