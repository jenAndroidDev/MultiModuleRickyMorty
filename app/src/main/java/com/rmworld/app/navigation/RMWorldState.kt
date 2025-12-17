package com.rmworld.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.rmworld.feature.detail.presentation.navigation.DetailRoute
import com.rmworld.feature.detail.presentation.navigation.navigateToDetailScreen
import com.rmworld.feature.home.presentation.navigation.navigateToHomeScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberRMWorldState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
):RMWorldState{

    return remember(
        coroutineScope,
        navController,
    ){
        RMWorldState(
            navController,
            coroutineScope
        )
    }
}
@Stable
class RMWorldState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    val currentDestination:NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    val topLevelDestinations:List<TopLevelDestinations> = TopLevelDestinations.entries

    fun navigateToTopLevelDestination(topLevelDestinations: TopLevelDestinations){

        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            popUpToTop(navController)
            launchSingleTop = true
            restoreState = true
        }
        when(topLevelDestinations){
            TopLevelDestinations.FEED->{
              navController.navigateToHomeScreen()
            }
            TopLevelDestinations.DETAIL->{
                navController.navigateToDetailScreen(DetailRoute(-1))
            }
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}