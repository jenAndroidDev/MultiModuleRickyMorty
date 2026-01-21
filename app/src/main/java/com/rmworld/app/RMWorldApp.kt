package com.rmworld.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import components.RMWorldNavigationSuiteScaffold
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
    val topLevelDestinations = TopLevelDestinations.entries.toTypedArray()
    val currentDestination = appState.currentDestination
    RMWorldNavigationSuiteScaffold(
        navigationSuiteItems = {
            topLevelDestinations.forEachIndexed { index, destination ->
                item(
                    selected = currentDestination.isTopLevelDestinationInHierarchy(destination = destination),
                    onClick = {
                        appState.navigateToTopLevelDestination(destination)
                    },
                    selectedIcon = destination.selectedIcon,
                    unSelectedIcon = destination.unselectedIcon,
                    label = {Text(destination.title)}

                )
            }
        }
    ) {
        Scaffold { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
            ) {
                RMWorldNavHost(
                    modifier = modifier,
                    appState = appState,
                    startDestination = startDestination
                )
            }
        }
    }
}

//Will be removed since we are using Navigation Suite
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

//-------Commented Code Just For Reference Will Remove it ---->
//Integrating Navigation Suite For Adaptive Layouts
/*Scaffold(
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
        .padding(top = innerPadding.calculateTopPadding())) {
        RMWorldNavHost(
            modifier = modifier,
            appState = appState,
            startDestination = startDestination
            )
    }
}*/
