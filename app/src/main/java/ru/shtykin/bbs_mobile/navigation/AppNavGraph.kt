package ru.shtykin.bbs_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    startScreenRoute: String,
    navHostController: NavHostController,
    camerasScreenContent: @Composable () -> Unit,
    doorsScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startScreenRoute
    ) {
        composable(Screen.Cameras.route) {
            camerasScreenContent()
        }
        composable(Screen.Doors.route) {
            doorsScreenContent()
        }
    }

}