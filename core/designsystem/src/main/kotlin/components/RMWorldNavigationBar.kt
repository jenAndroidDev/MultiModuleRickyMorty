package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.RickAndMortyTheme

private val navigationBarShape = RoundedCornerShape(
    topEnd = 12.dp,
    topStart = 12.dp,
    bottomStart = 28.dp,
    bottomEnd = 28.dp)


@Composable
fun RMWorldNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .clip(navigationBarShape),
        containerColor = RickAndMortyTheme.colors.surface
            .copy(alpha = 0.95f),
        tonalElevation = 0.dp,
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
    unSelectedImageVector: ImageVector,
    selectedImageVector: ImageVector,
    label: @Composable (() -> Unit)? = null
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) RMWorldGradientBottomNavigationIcon(imageVector =
                selectedImageVector,
                contentDescription = "",
                selected = true
            ) else{
                RMWorldGradientBottomNavigationIcon(
                    imageVector = unSelectedImageVector,
                    contentDescription = "",
                    selected = false
                )
            }
        },
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        label = label,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = Color.Unspecified,
            unselectedIconColor = RickAndMortyTheme.colors.textSecondary,
            selectedTextColor = RickAndMortyTheme.colors.tertiary,
            unselectedTextColor = RickAndMortyTheme.colors.textSecondary
        )
    )
}
@Composable
fun RMWorldGradientBottomNavigationIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    selected: Boolean,
    size: Dp = 24.dp
) {
    val gradientColorList = listOf(
        RickAndMortyTheme.colors.tertiary,
        RickAndMortyTheme.colors.onTertiary,
        RickAndMortyTheme.colors.tertiaryContainer
    )
    if (selected) {
        Box(
            modifier = Modifier
                .size(size)
                .background(
                    brush = Brush.radialGradient(
                        colors = gradientColorList
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = Color.Black
            )
        }
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}
