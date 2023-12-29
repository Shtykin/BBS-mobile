package ru.shtykin.bbs_mobile.domain.usecase

import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.entity.Door

class UpdateAllDoorsToDbUseCase (private val repository: Repository) {
    suspend fun execute(doors: List<Door>) {
        repository.deleteAllDoorsFromDb()
        repository.saveDoorsToDb(doors)
    }

}