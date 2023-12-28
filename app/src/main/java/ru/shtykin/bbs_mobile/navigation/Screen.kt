package ru.shtykin.bbs_mobile.navigation

sealed class Screen(
    val route: String
) {
    object Cameras: Screen(ROUTE_CAMERAS)
    object Doors: Screen(ROUTE_DOORS)

    private companion object {
        const val ROUTE_CAMERAS = "cameras"
        const val ROUTE_DOORS = "doors"
    }
}
