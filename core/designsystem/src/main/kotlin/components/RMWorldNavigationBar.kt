package components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.RickAndMortyTheme

private val navigationBarShape = RoundedCornerShape(
    topEnd = 12.dp,
    topStart = 12.dp,
    bottomEnd = 28.dp,
    bottomStart = 28.dp
)

@Composable
fun RMWorldNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .clip(navigationBarShape),
        tonalElevation = 0.dp,
        containerColor = RickAndMortyTheme.colors.background.copy(alpha = 0.95f),
        windowInsets = WindowInsets(0, 0, 0, 0),
        content = content,
    )
}

@Composable
fun RowScope.RMWorldBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        label = label,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = RickAndMortyTheme.colors.tertiary,
            selectedTextColor = RickAndMortyTheme.colors.tertiary,
            indicatorColor = Color.Transparent,

            unselectedIconColor = RickAndMortyTheme.colors.textSecondary,
            unselectedTextColor = RickAndMortyTheme.colors.textSecondary,

            disabledIconColor = Color.Gray.copy(alpha = 0.4f),
            disabledTextColor = Color.Gray.copy(alpha = 0.4f)
        )
    )
}
