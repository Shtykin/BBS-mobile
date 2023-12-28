package ru.shtykin.bbs_mobile.data.repository

import android.util.Log
import ru.shtykin.bbs_mobile.data.mapper.Mapper
import ru.shtykin.bbs_mobile.data.network.ApiService
import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.entity.Door

class RepositoryImpl(
    private val apiService: ApiService,
    private val mapper: Mapper,
) : Repository {

    override suspend fun getCameras(): List<Camera> {
        val response = apiService.getCameras().execute()
        response.body()?.let {
            return mapper.mapResponseCamerasDtoToCameras(it)
        }
        throw IllegalStateException("Response body is wrong")
    }

    override suspend fun getDoors(): List<Door> {
        val response = apiService.getDoors().execute()
        response.body()?.let {
            return mapper.mapResponseDoorsDtoToDoors(it)
        }
        throw IllegalStateException("Response body is wrong")
    }
}