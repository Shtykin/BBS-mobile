package ru.shtykin.bbs_mobile.domain

import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.entity.Door

interface Repository {
    suspend fun getCameras() : List<Camera>
    suspend fun getDoors() : List<Door>
}