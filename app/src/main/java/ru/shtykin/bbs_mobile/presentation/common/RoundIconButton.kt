package ru.shtykin.bbs_mobile.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.shtykin.bbs_mobile.presentation.ui.theme.Gold1
import ru.shtykin.bbs_mobile.presentation.ui.theme.LightGray1

@Composable
fun RoundIconButton(
    imageVector: ImageVector,
    color: Color,
    onClick: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .border(
                width = 1.dp,
                color = LightGray1,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = imageVector,
            modifier = Modifier.padding(8.dp).clickable{onClick?.invoke()},
            contentDescription = null,
            tint = color
        )
    }
}