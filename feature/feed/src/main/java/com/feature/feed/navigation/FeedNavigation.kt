package com.feature.feed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.feature.feed.FeedRoute

const val FEED_ROUTE = "feed_route"

fun NavController.navigateToFeedScreen() = navigate(FEED_ROUTE)

fun NavGraphBuilder.feedScreen() {
    composable(
        route = FEED_ROUTE
    ) {
        FeedRoute()
    }
}