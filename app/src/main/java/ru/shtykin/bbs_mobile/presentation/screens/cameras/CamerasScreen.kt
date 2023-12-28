package ru.shtykin.bbs_mobile.presentation.screens.cameras

import android.annotation.SuppressLint
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
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.presentation.common.RoundIconButton
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import ru.shtykin.bbs_mobile.presentation.ui.theme.Gold1

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasScreen(
    uiState: ScreenState,
    onFavoriteClick: ((Camera) -> Unit)?,
    onSwipeRefresh: (() -> Unit)?,
) {
    val cameras = (uiState as? ScreenState.CamerasScreen)?.cameras ?: emptyList()
    val refreshing = (uiState as? ScreenState.CamerasScreen)?.refreshing ?: false

    val pullRefreshState = rememberPullRefreshState(refreshing, { onSwipeRefresh?.invoke()})

        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(cameras) {
                    SwipeCameraBox(
                        camera = it,
                        onFavoriteClick = { onFavoriteClick?.invoke(it) }
                    )
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }



}

@Composable
fun CameraCard(
    camera: Camera
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
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                painter = rememberAsyncImagePainter(camera.snapshot),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            if (camera.favorites)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        modifier = Modifier.padding(8.dp),
                        contentDescription = null,
                        tint = Gold1
                    )
                }

        }
        Text(
            text = camera.name,
            fontSize = 19.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun SwipeCameraBox(
    camera: Camera,
    onFavoriteClick: (() -> Unit)?,
) {
    SwipeBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 60.dp,
        endContent = { _, _ ->
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                RoundIconButton(
                    imageVector = if (camera.favorites) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                    color = Gold1,
                    onClick = {onFavoriteClick?.invoke()}
                )
            }
        }
    ) { _, _, _ ->
        CameraCard(camera)
    }
}