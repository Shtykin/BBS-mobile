package ru.shtykin.bbs_mobile.domain

interface Repository {
    suspend fun getCameras()
    suspend fun getDoors()
}