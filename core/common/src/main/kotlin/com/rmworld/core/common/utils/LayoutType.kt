package com.rmworld.core.common.utils

import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass.Companion.COMPACT
import androidx.window.core.layout.WindowWidthSizeClass.Companion.EXPANDED
import androidx.window.core.layout.WindowWidthSizeClass.Companion.MEDIUM


fun WindowSizeClass.getDisplayType(): RMWorldDisplayTypes{
   return when(this.windowWidthSizeClass){
        COMPACT -> RMWorldDisplayTypes.MOBILE_PORTRAIT
        MEDIUM -> RMWorldDisplayTypes.MOBILE_LANDSCAPE
        EXPANDED -> RMWorldDisplayTypes.TABLET_PORTRAIT
        else -> {
           RMWorldDisplayTypes.UNDEFINED
       }
   }
}
enum class RMWorldDisplayTypes{
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    UNDEFINED,
    TABLET_LANDSCAPE
}