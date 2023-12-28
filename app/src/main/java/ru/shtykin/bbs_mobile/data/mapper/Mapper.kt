package ru.shtykin.bbs_mobile.data.mapper

import ru.shtykin.bbs_mobile.data.network.model.cameras.ResponseCamerasDto
import ru.shtykin.bbs_mobile.data.network.model.doors.ResponseDoorsDto
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.entity.Door

class Mapper {

    fun mapResponseCamerasDtoToCameras(responseCamerasDto: ResponseCamerasDto): List<Camera> =
        responseCamerasDto.data.cameras.map {
            Camera(
                id = it.id,
                name = it.name,
                room = it.room,
                snapshot = it.snapshot,
                favorites = it.favorites,
                rec = it.rec,
            )
        }

    fun mapResponseDoorsDtoToDoors(responseDoorsDto: ResponseDoorsDto): List<Door> =
        responseDoorsDto.data.map {
            Door(
                id = it.id,
                name = it.name,
                room = it.room,
                snapshot = it.snapshot,
                favorites = it.favorites,
            )
        }

}