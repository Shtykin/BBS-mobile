package ru.shtykin.bbs_mobile.presentation.state

import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.entity.Door


sealed class ScreenState {

    data class CamerasScreen(
        val cameras: List<Camera>,
        val refreshing: Boolean,
        val error: String?
    ) : ScreenState()

    data class DoorsScreen(
        val doors: List<Door>,
        val refreshing: Boolean,
        val error: String?
    ) : ScreenState()
}
