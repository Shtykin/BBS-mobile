package ru.shtykin.bbs_mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.shtykin.bbs_mobile.navigation.AppNavGraph
import ru.shtykin.bbs_mobile.navigation.MenuItem
import ru.shtykin.bbs_mobile.navigation.Screen
import ru.shtykin.bbs_mobile.presentation.nav_menu.NavMenu
import ru.shtykin.bbs_mobile.presentation.screens.cameras.CamerasScreen
import ru.shtykin.bbs_mobile.presentation.screens.doors.DoorsScreen
import ru.shtykin.bbs_mobile.presentation.ui.theme.BBSmobileTheme

@AndroidEntryPoint
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
                                    is MenuItem.Cameras -> {
                                        if (navHostController.currentDestination?.route != Screen.Cameras.route) {
                                            viewModel.getCameras()
                                            navHostController.navigate(Screen.Cameras.route){
                                                popUpTo(0)
                                            }
                                        }
                                    }
                                    is MenuItem.Doors -> {
                                        if (navHostController.currentDestination?.route != Screen.Doors.route) {
                                            viewModel.getDoors()
                                            navHostController.navigate(Screen.Doors.route) {
                                                popUpTo(0)
                                            }
                                        }
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
                                    onFavoriteClick = {viewModel.toggleFavoriteCamera(it)},
                                    onSwipeRefresh = { viewModel.getCamerasIntend()}
                                )
                            },
                            doorsScreenContent = {
                                DoorsScreen(
                                    uiState = uiState,
                                    onFavoriteClick = {},
                                    onEditClick = {},
                                    onSwipeRefresh = { viewModel.getDoorsIntend()}
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

