package ru.shtykin.bbs_mobile.navigation

sealed class MenuItem(
    val title: String,
) {

    object Cameras: MenuItem(
        title = "Камеры",
    )

    object Doors: MenuItem(
        title = "Двери",
    )
}