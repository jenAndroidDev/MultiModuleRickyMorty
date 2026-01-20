package components

import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.RickAndMortyTheme

@Composable
fun RMWorldNavigationSuiteScaffold(
    navigationSuiteItems: RMWorldNavigationSuiteScope.()-> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit
){
    val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
        windowAdaptiveInfo
    )
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationRailItemColors = NavigationRailItemDefaults.colors(

        ),
        navigationBarItemColors = NavigationBarItemDefaults.colors(

        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        )
    )
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            RMWorldNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors).run(navigationSuiteItems)

        },
        layoutType = layoutType,
        modifier = modifier,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationRailContainerColor = RickAndMortyTheme.colors.background.copy(alpha = 0.95f),
            navigationBarContainerColor = RickAndMortyTheme.colors.background.copy(alpha = 0.9f)
        )
    ){
        content()
    }
}

class RMWorldNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

object RMWorldNavigationDefaults{

    @Composable
    fun navigationColorUnSpecified() = Color.Unspecified

    @Composable
    fun navigationSelectedItemColor() = Color.Unspecified

    @Composable
    fun navigationContentColor() = RickAndMortyTheme.colors.background

    @Composable
    fun navigationSelectedTextColor() = RickAndMortyTheme.colors.tertiary

    @Composable
    fun navigationUnSelectedTextColor() = RickAndMortyTheme.colors.textSecondary

}