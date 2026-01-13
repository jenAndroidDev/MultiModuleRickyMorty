package com.rmworld.app

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.rmworld.app.navigation.RMWorldNavHost
import com.rmworld.app.navigation.RMWorldState
import com.rmworld.app.navigation.TopLevelDestinations
import components.RMWorldBarItem
import components.RMWorldNavigationBar
import theme.RickAndMortyTheme
import kotlin.reflect.KClass

private const val Tag = "RMWorldApp"
@Composable
fun RMWorldApp(
    appState: RMWorldState,
    modifier: Modifier = Modifier,
    startDestination: KClass<*>
    ){
    RMWorld(
        appState = appState,
        modifier = modifier,
        startDestination = startDestination
    )
}

@Composable
internal fun RMWorld(
    appState: RMWorldState,
    modifier: Modifier,
    startDestination: KClass<*>,
    variance: String = "Default",
) {
    Scaffold(
        containerColor = RickAndMortyTheme.colors.background,
        bottomBar = {
        RMWorldBottomBar(
            destinations = appState.topLevelDestinations,
            currentDestination = appState.currentDestination,
            modifier = modifier,
            onNavigateToDestination = appState::navigateToTopLevelDestination,
            appState = appState
        )
    }) { innerPadding ->
        Log.d("scaffold", "ComposeSignUpApp() called...$innerPadding")
        Column(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            RMWorldNavHost(
                modifier = modifier,
                appState = appState,
                startDestination = startDestination
                )
        }
    }
}

@Composable
private fun RMWorldBottomBar(
    modifier: Modifier,
    destinations: List<TopLevelDestinations>,
    appState: RMWorldState,
    currentDestination:NavDestination?,
    onNavigateToDestination:(TopLevelDestinations)->Unit,
){
    RMWorldNavigationBar(modifier = modifier.fillMaxWidth()) {
        destinations.forEach { topLevelDestination ->
            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination)
            RMWorldBarItem(
                selected = isSelected,
                label = {
                    Text(topLevelDestination.title)
                },
                selectedImageVector = topLevelDestination.selectedIcon,
                unSelectedImageVector = topLevelDestination.unselectedIcon,
                onClick = {
                    onNavigateToDestination.invoke(topLevelDestination)
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinations) =
    this?.hierarchy?.any { it.hasRoute(destination.route) } ?: false
