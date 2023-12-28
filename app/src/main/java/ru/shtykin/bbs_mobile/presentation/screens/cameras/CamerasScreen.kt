package ru.shtykin.bbs_mobile.presentation.screens.cameras

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ru.shtykin.bbs_mobile.presentation.state.ScreenState

@Composable
fun CamerasScreen(
    uiState: ScreenState
) {
    Column {

        LazyColumn() {
            item { CameraCard() }
            item { CameraCard() }
        }

    }
}

@Composable
fun CameraCard(

) {
    Card {
        Image(
            modifier = Modifier.fillMaxWidth().heightIn(min = 50.dp),
            painter = rememberAsyncImagePainter("https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png"),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(text = "Камера 1")
    }
}