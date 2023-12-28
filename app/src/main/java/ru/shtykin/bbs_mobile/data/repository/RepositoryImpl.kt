package ru.shtykin.bbs_mobile.data.repository

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import ru.shtykin.bbs_mobile.data.db.model.CameraDbModel
import ru.shtykin.bbs_mobile.data.mapper.Mapper
import ru.shtykin.bbs_mobile.data.network.ApiService
import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.entity.Camera
import ru.shtykin.bbs_mobile.domain.entity.Door

class RepositoryImpl(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val realm: Realm
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

    override suspend fun saveCameraToDb(camera: Camera) {
        realm.writeBlocking {
            copyToRealm(mapper.mapCameraToCameraDbModel(camera))
        }
    }

    override suspend fun getCamerasFromDb(): List<Camera> {
        return realm.query<CameraDbModel>().find().toList().map { mapper.mapCameraDbModelToCamera(it) }
    }

    override suspend fun deleteAllCamerasFromDb() {
        realm.write {
            val items = query<CameraDbModel>().find()
            delete(items)
        }
    }

    override suspend fun saveCamerasToDb(cameras: List<Camera>) {
        realm.write {
            cameras.forEach{
                val cameraDbModel = mapper.mapCameraToCameraDbModel(it)
                copyToRealm(cameraDbModel)
            }
        }
    }
}