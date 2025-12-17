package com.rmworld.app.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.rmworld.feature.detail.presentation.navigation.DetailRoute
import com.rmworld.feature.home.presentation.navigation.HomeRoute
import kotlin.reflect.KClass

enum class TopLevelDestinations(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val route: KClass<*>

) {
    FEED(
        title = "For You",
        selectedIcon = RMWorldIcons.HomeSelected,
        unselectedIcon = RMWorldIcons.HomeUnSelected,
        route = HomeRoute::class
        ),
    DETAIL(
        title = "Search",
        selectedIcon = RMWorldIcons.AccountSelected,
        unselectedIcon = RMWorldIcons.AccountUnSelected,
        route = DetailRoute::class
    )
}