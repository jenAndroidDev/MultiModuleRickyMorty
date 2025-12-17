package com.rmworld.feature.detail.presentation.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rmworld.feature.detail.presentation.DetailRoute
import kotlinx.serialization.Serializable


@Serializable data class DetailRoute(val id: Int)

fun NavController.navigateToDetailScreen(route: DetailRoute) = navigate(route = route)

fun NavGraphBuilder.detailScreen(){
    composable<DetailRoute> {backStackEntry->
        val detail: DetailRoute = backStackEntry.toRoute()
        val characterId = detail.id
        Log.d("Detail Route", "detailScreen() called with: backStackEntry = $characterId")
        DetailRoute(characterId)
    }
}

