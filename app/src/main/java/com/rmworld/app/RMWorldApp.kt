package com.rmworld.app

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
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
    }) {
        Log.d("scaffold", "ComposeSignUpApp() called...$it")
        Column(modifier = modifier.fillMaxSize()) {
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
        destinations.onEachIndexed { index, topLevelDestination ->

            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination)

            Log.d(
                "RMWorldNavigationBar",
                "RMWorldBottomBar() called with: index = $isSelected, topLevelDestination = ${topLevelDestination.route}"
            )
            RMWorldBarItem(
                selected = appState.currentDestination?.route==topLevelDestination.name,
                icon = {
                    Icon(
                        imageVector = destinations[index].selectedIcon,
                        contentDescription = destinations[index].title
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destinations[index].unselectedIcon,
                        contentDescription = topLevelDestination.title
                    )
                },
                label = {
                    Text(topLevelDestination.title)
                },
                onClick = {
                    onNavigateToDestination.invoke(topLevelDestination)
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
