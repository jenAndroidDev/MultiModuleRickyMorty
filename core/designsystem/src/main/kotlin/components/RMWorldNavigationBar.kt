package components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.RickAndMortyTheme

@Composable
fun RMWorldNavigationBar(
    modifier: Modifier,
    content:@Composable RowScope.()->Unit
){
    NavigationBar(
        modifier = modifier,
        containerColor = RickAndMortyTheme.colors.brandSecondary,
        contentColor = Color.Gray.copy(alpha = 0.3f),
        tonalElevation = 0.dp,
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
            selectedIconColor = RickAndMortyTheme.colors.textPrimary,
            selectedTextColor = RickAndMortyTheme.colors.textPrimary,
            indicatorColor = RickAndMortyTheme.colors.textPrimary,

            unselectedIconColor = RickAndMortyTheme.colors.textSecondary,
            unselectedTextColor = RickAndMortyTheme.colors.textSecondary,

            disabledIconColor = Color.Gray.copy(alpha = 0.4f),
            disabledTextColor = Color.Gray.copy(alpha = 0.4f)
        )
    )
}