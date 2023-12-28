package ru.shtykin.bbs_mobile.presentation.state


sealed class ScreenState {

    data class CamerasScreen(
        val temp: String,
    ) : ScreenState()

    data class DoorsScreen(
        val temp: String,
    ) : ScreenState()
}
