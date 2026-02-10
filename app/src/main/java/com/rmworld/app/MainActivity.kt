package com.rmworld.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rmworld.app.navigation.rememberRMWorldState
import com.rmworld.feature.home.presentation.navigation.HomeRoute
import dagger.hilt.android.AndroidEntryPoint
import theme.RickAndMortyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
               println()
                val appState = rememberRMWorldState()
                RMWorldApp(appState, startDestination = HomeRoute::class)
            }
        }
    }
}
