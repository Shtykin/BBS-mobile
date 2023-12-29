package ru.shtykin.bbs_mobile.domain.usecase

import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.entity.Camera

class ToggleFavoriteCameraInDbUseCase (private val repository: Repository) {
    suspend fun execute(camera: Camera) {
        repository.updateCamerasInDbWithId(camera.copy(favorites = !camera.favorites))
    }

}