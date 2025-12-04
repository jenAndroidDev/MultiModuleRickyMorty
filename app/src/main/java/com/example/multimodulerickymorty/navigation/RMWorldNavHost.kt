package com.example.multimodulerickymorty.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.feature.feed.navigation.feedScreen
import com.feature.home.presentation.navigation.homeScreen
import com.mmd.feature.presentation.navigation.detailScreen

/*
* All */
private const val Tag = "RMWorldNavHost"
@Composable
fun RMWorldNavHost(
    modifier: Modifier,
    appState: RMWorldState,
    startDestination:String,

    ){
    val navController = appState.navController

    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        feedScreen()
        detailScreen()
        homeScreen()
    }

}