@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationApi::class)

package com.rmworld.app.navigation


import android.telecom.Call
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rmworld.feature.detail.presentation.navigation.DetailRoute
import com.rmworld.feature.detail.presentation.navigation.detailScreen
import com.rmworld.feature.detail.presentation.navigation.navigateToDetailScreen
import com.rmworld.feature.home.presentation.navigation.HomeRoute
import com.rmworld.feature.home.presentation.navigation.homeScreen
import theme.LocalAnimatedVisibilityScope
import theme.LocalSharedTransitionScope
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Composable
fun RMWorldNavHost(
    modifier: Modifier,
    appState: RMWorldState,
    startDestination: KClass<*>,
    ){
    val navController = appState.navController

    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this@SharedTransitionLayout) {
            NavHost(navController = navController,
                startDestination = startDestination,
                modifier = modifier){

                homeScreen { characterId ->
                    navController.navigateToDetailScreen(DetailRoute(characterId))
                }

                detailScreen(
                )
            }
        }
    }
}



@Composable
private fun SampleTest(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Log.d("Sample Test", "SampleTest() called")
        Text("Composition Provider Works")
    }
}


