package ru.shtykin.bbs_mobile.presentation.state

import ru.shtykin.bbs_mobile.domain.entity.Camera


sealed class ScreenState {

    data class CamerasScreen(
        val cameras: List<Camera>
    ) : ScreenState()

    data class DoorsScreen(
        val temp: String,
    ) : ScreenState()
}
