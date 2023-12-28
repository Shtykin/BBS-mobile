package ru.shtykin.bbs_mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.shtykin.bbs_mobile.navigation.AppNavGraph
import ru.shtykin.bbs_mobile.navigation.Screen
import ru.shtykin.bbs_mobile.presentation.screens.cameras.CamerasScreen
import ru.shtykin.bbs_mobile.presentation.screens.doors.DoorsScreen
import ru.shtykin.bbs_mobile.presentation.ui.theme.BBSmobileTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            val scope = rememberCoroutineScope()
            val uiState by viewModel.uiState
            val startScreenRoute = Screen.Cameras.route

            BBSmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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