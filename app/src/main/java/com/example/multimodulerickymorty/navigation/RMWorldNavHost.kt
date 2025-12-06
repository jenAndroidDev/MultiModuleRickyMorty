package com.example.multimodulerickymorty.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.feature.feed.navigation.feedScreen
import com.feature.home.presentation.navigation.homeScreen
import com.mmd.feature.presentation.navigation.detailScreen
import com.mmd.feature.presentation.navigation.navigateToDetailScreen
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
        //feedScreen()
        homeScreen{characterId->
            Log.d(Tag, "RMWorldNavHost() called with: characterId = $characterId")
            navController.navigateToDetailScreen()
        }
        detailScreen()

    }

}