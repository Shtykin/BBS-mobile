package ru.shtykin.bbs_mobile.presentation.screens.doors

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kevinnzou.compose.swipebox.SwipeBox
import com.kevinnzou.compose.swipebox.SwipeDirection
import ru.shtykin.bbs_mobile.domain.entity.Door
import ru.shtykin.bbs_mobile.presentation.common.RoundIconButton
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import ru.shtykin.bbs_mobile.presentation.ui.theme.DarkCyan1
import ru.shtykin.bbs_mobile.presentation.ui.theme.Gold1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorsScreen(
    uiState: ScreenState,
    onFavoriteClick: ((Door) -> Unit)?,
    onEditClick: ((Door) -> Unit)?,
    onSwipeRefresh: (() -> Unit)?,
) {
    val doors = (uiState as? ScreenState.DoorsScreen)?.doors ?: emptyList()
    val refreshing = (uiState as? ScreenState.DoorsScreen)?.refreshing ?: false
    val error = (uiState as? ScreenState.DoorsScreen)?.error

    val pullRefreshState = rememberPullRefreshState(refreshing, { onSwipeRefresh?.invoke() })

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (error != null) {
                item {
                    Text(
                        text = "Oops... Something wrong...\n$error",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 14.sp,
                        color = DarkCyan1
                    )
                }
            }
            items(doors) {
                SwipeDoorBox(
                    door = it,
                    onFavoriteClick = { onFavoriteClick?.invoke(it) },
                    onEditClick = { onEditClick?.invoke(it) }
                )
            }
        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun DoorCard(
    door: Door
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        if (!door.snapshot.isNullOrEmpty()) {
            Box() {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    painter = rememberAsyncImagePainter(door.snapshot),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = door.name,
                fontSize = 19.sp,
                modifier = Modifier.padding(8.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Lock,
                modifier = Modifier.padding(8.dp),
                contentDescription = null,
                tint = DarkCyan1
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun SwipeDoorBox(
    door: Door,
    onFavoriteClick: (() -> Unit)?,
    onEditClick: (() -> Unit)?,
) {
    SwipeBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 120.dp,
        endContent = { _, _ ->
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    RoundIconButton(
                        imageVector = Icons.Outlined.BorderColor,
                        color = DarkCyan1,
                        onClick = { onEditClick?.invoke() }
                    )
                    RoundIconButton(
                        imageVector = if (door.favorites) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                        color = Gold1,
                        onClick = { onFavoriteClick?.invoke() }
                    )
                }
            }
        }
    ) { _, _, _ ->
        DoorCard(door)
    }
}