package ru.shtykin.bbs_mobile.domain.usecase

import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.entity.Camera

class UpdateAllCamerasToDbUseCase (private val repository: Repository) {
    suspend fun execute(cameras: List<Camera>) {
        repository.deleteAllCamerasFromDb()
        repository.saveCamerasToDb(cameras)
    }

}