package com.example.multimodulerickymorty.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.rmworld.feature.detail.presentation.navigation.detailScreen
import com.rmworld.feature.detail.presentation.navigation.navigateToDetailScreen
import com.rmworld.feature.home.presentation.navigation.homeScreen
import kotlin.reflect.KClass

/*
* All */
private const val Tag = "RMWorldNavHost"
@Composable
fun RMWorldNavHost(
    modifier: Modifier,
    appState: RMWorldState,
    startDestination: KClass<*>,

    ){
    val navController = appState.navController

    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        homeScreen {
            navController.navigateToDetailScreen()
        }
        detailScreen()

    }

}