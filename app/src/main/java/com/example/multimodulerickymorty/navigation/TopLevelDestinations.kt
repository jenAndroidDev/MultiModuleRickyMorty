package com.example.multimodulerickymorty.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.feature.home.presentation.navigation.HomeRoute
import com.mmd.feature.presentation.navigation.DetailRoute
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