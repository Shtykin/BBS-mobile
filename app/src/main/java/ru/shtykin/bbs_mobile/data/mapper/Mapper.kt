package ru.shtykin.bbs_mobile.data.mapper

import ru.shtykin.bbs_mobile.data.db.model.CameraDbModel
import ru.shtykin.bbs_mobile.data.db.model.DoorDbModel
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

    fun mapCameraToCameraDbModel(camera: Camera) =
        CameraDbModel().apply {
            camera_id = camera.id
            name = camera.name
            room = camera.room ?: ""
            snapshot = camera.snapshot
            favorites = camera.favorites
            rec = camera.rec
        }

    fun mapCameraDbModelToCamera(cameraDbModel: CameraDbModel) =
        Camera(
            id = cameraDbModel.camera_id,
            name = cameraDbModel.name,
            room = cameraDbModel.room,
            snapshot = cameraDbModel.snapshot,
            favorites = cameraDbModel.favorites,
            rec = cameraDbModel.rec
        )

    fun mapDoorToDoorDbModel(door: Door) =
        DoorDbModel().apply {
            door_id = door.id
            name = door.name
            room = door.room ?: ""
            snapshot = door.snapshot ?: ""
            favorites = door.favorites
        }
    fun mapDoorDbModelToDoor(doorDbModel: DoorDbModel) =
        Door(
            id = doorDbModel.door_id,
            name = doorDbModel.name,
            room = doorDbModel.room,
            snapshot = doorDbModel.snapshot.ifEmpty { null },
            favorites = doorDbModel.favorites
        )

}