package com.rmworld.core.common.extensions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

fun LazyListState.isFirstItemVisible() = firstVisibleItemIndex == 0

@Composable
fun LazyListState.isScrollingDown(): Boolean {
    val offset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) { derivedStateOf { (firstVisibleItemScrollOffset - offset) > 0 } }.value
}