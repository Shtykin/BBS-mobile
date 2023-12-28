package ru.shtykin.bbs_mobile.presentation.screens.cameras

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kevinnzou.compose.swipebox.SwipeBox
import com.kevinnzou.compose.swipebox.SwipeDirection
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.presentation.state.ScreenState
import ru.shtykin.bbs_mobile.presentation.ui.theme.Gold1
import ru.shtykin.bbs_mobile.presentation.ui.theme.LightGray1

@Composable
fun CamerasScreen(
    uiState: ScreenState,
    onFavoriteClick: ((Camera) -> Unit)?,
) {
    val cameras = (uiState as? ScreenState.CamerasScreen)?.cameras ?: emptyList()
    Column {

        LazyColumn() {
            items(cameras) {
                SwipeCameraBox(
                    camera = it,
                    onFavoriteClick = {onFavoriteClick?.invoke(it)}
                )
            }
        }
    }
}

@Composable
fun CameraCard(
    camera: Camera
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
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
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = LightGray1,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (camera.favorites) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                        modifier = Modifier.padding(8.dp).clickable{onFavoriteClick?.invoke()},
                        contentDescription = null,
                        tint = Gold1
                    )
                }
            }
        }
    ) { _, _, _ ->
        CameraCard(camera)
    }
}