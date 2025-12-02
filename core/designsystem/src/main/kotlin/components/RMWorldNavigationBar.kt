package components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RMWorldNavigationBar(
    modifier: Modifier,
    content:@Composable RowScope.()->Unit
){
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black.copy(alpha = 0.4f),
        contentColor = Color.Gray.copy(alpha = 0.3f),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun RowScope.RMWorldBarItem(
    selected:Boolean,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    alwaysShowLabel:Boolean =true,
    icon:@Composable ()->Unit,
    selectedIcon:@Composable ()->Unit,
    label: @Composable (()->Unit)?=null
){
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = {
            onClick.invoke()
                  },
        icon = if (selected) selectedIcon else icon,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        label = label
        )

}