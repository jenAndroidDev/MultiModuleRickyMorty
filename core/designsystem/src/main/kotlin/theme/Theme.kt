

@file:OptIn(ExperimentalSharedTransitionApi::class)

package theme

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val darkColorPalette = RickAndMortyColors(
    brand = GREY,
    brandSecondary = GREY_WHITE,
    background = BACKGROUND,
    textPrimary = GREY_WHITE,
    textSecondary = GREY,
    tertiary = TERTIARY,
    onTertiary = ON_TERTIARY,
    tertiaryContainer = TERTIARY_CONTAINER,
    surface = BLACK_GREY_60
)

private val lightColorPalette = RickAndMortyColors(
    brand = GREY_WHITE,
    brandSecondary = GOLDEN_YELLOW,
    background = GREY_10,
    textPrimary = GOLDEN_YELLOW,
    textSecondary = BLACK_GREY_60,
    tertiary = NeonYellowDark,
    onTertiary = NeonYellowDeep,
    tertiaryContainer = NeonYellowGlow,
    surface = GREY_WHITE
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors = if(darkTheme){
        darkColorPalette
    }else{
        lightColorPalette
    }
    ProvideRickAndMortyColors(colors, content = content)

}

object RickAndMortyTheme{
    val colors: RickAndMortyColors
        @Composable
        get() = LocalRickAndMortyColors.current
}

@Composable
fun ProvideRickAndMortyColors(
    colors: RickAndMortyColors,
    content: @Composable () -> Unit
){
    CompositionLocalProvider(LocalRickAndMortyColors provides colors, content = content)
}

private val LocalRickAndMortyColors = staticCompositionLocalOf<RickAndMortyColors> {
    error("No RickAndMortyColorPalette provided")
}


@Immutable
data class RickAndMortyColors(
    val brand: Color,
    val brandSecondary: Color,
    val background: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val surface: Color
)


val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope> {
    error("No SharedTransitionScope provided")
}
val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope> {
    error("No AnimatedVisibilityScope provided")
}

