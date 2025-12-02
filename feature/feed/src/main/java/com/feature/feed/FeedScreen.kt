package com.feature.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FeedRoute(){
    FeedScreen()
}
@Composable
fun FeedScreen(){

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text("Feed Screen")
    }
}