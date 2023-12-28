package ru.shtykin.bbs_mobile.presentation.nav_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.shtykin.bbs_mobile.navigation.MenuItem
import ru.shtykin.bbs_mobile.presentation.ui.theme.DarkCyan1
import ru.shtykin.bbs_mobile.presentation.ui.theme.LightGray1
import ru.shtykin.bbs_mobile.presentation.ui.theme.circeFamily

@Composable
fun NavMenu(
    items: List<MenuItem>,
    onItemClick: ((MenuItem) -> Unit)?,
) {
    val currentItem = remember { mutableStateOf(MenuItem.Cameras as MenuItem) }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items.forEach {
            Item(
                item = it,
                onItemClick = {
                    currentItem.value = it
                    onItemClick?.invoke(it)
                },
                modifier = Modifier.weight(1f),
                enabled = currentItem.value == it
            )
        }
    }
}

@Composable
fun Item(
    item: MenuItem,
    onItemClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) {
    TextButton(
        onClick = { onItemClick?.invoke() },
        modifier = modifier,
        contentPadding = PaddingValues(),
        shape = RectangleShape
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.title,
                    fontSize = 17.sp,
                    fontFamily = circeFamily,
                    color = Color.Black
                )
            }
            Divider(thickness = 2.dp, color = if (enabled) DarkCyan1 else LightGray1)
        }
    }
}