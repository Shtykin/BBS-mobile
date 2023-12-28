package ru.shtykin.bbs_mobile.domain.usecase

import ru.shtykin.bbs_mobile.domain.Repository

class GetDoorsUseCase (private val repository: Repository) {
    suspend fun execute() =
        repository.getDoors()
}