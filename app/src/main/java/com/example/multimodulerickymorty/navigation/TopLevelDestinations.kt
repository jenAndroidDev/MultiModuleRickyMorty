package com.example.multimodulerickymorty.navigation

import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestinations(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
) {
    FEED(
        title = "For You",
        selectedIcon = RMWorldIcons.HomeSelected,
        unselectedIcon = RMWorldIcons.HomeUnSelected,
        ),
    DETAIL(
        title = "Search",
        selectedIcon = RMWorldIcons.AccountSelected,
        unselectedIcon = RMWorldIcons.AccountUnSelected,
    )
}