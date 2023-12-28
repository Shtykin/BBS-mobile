package ru.shtykin.bbs_mobile.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ru.shtykin.bbs_mobile.navigation.AppNavGraph
import ru.shtykin.bbs_mobile.navigation.MenuItem
import ru.shtykin.bbs_mobile.navigation.Screen
import ru.shtykin.bbs_mobile.presentation.nav_menu.NavMenu
import ru.shtykin.bbs_mobile.presentation.screens.cameras.CamerasScreen
import ru.shtykin.bbs_mobile.presentation.screens.doors.DoorsScreen
import ru.shtykin.bbs_mobile.presentation.ui.theme.BBSmobileTheme
import ru.shtykin.bbs_mobile.presentation.ui.theme.DarkCyan1
import ru.shtykin.bbs_mobile.presentation.ui.theme.LightGray1
import ru.shtykin.bbs_mobile.presentation.ui.theme.circeFamily

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            val uiState by viewModel.uiState
            val startScreenRoute = Screen.Cameras.route

            BBSmobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Title("Мой дом")
                        NavMenu(
                            items = listOf(MenuItem.Cameras, MenuItem.Doors),
                            onItemClick = {
                                when (it) {
                                    is MenuItem.Cameras -> navHostController.navigate(Screen.Cameras.route) {
                                        popUpTo(Screen.Cameras.route) { inclusive = true }
                                    }

                                    is MenuItem.Doors -> navHostController.navigate(Screen.Doors.route) {
                                        popUpTo(Screen.Doors.route) { inclusive = true }
                                    }
                                }
                            },
                        )
                        AppNavGraph(
                            startScreenRoute = startScreenRoute,
                            navHostController = navHostController,
                            camerasScreenContent = {
                                CamerasScreen(
                                    uiState = uiState,
                                )
                            },
                            doorsScreenContent = {
                                DoorsScreen(
                                    uiState = uiState,
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Title(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 21.sp
        )
    }
}

