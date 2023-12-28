package ru.shtykin.bbs_mobile.data.repository

import android.util.Log
import ru.shtykin.bbs_mobile.data.network.ApiService
import ru.shtykin.bbs_mobile.domain.Repository

class RepositoryImpl(
    private val apiService: ApiService,
) : Repository {

    override suspend fun getCameras() {
        try {
            val response = apiService.getCameras().execute()
            Log.e("DEBUG1", "resp -> $response")
            response.body()?.let {
                Log.e("DEBUG1", "body -> $it")
            }
        } catch (e: Exception) {
            Log.e("DEBUG1", "e -> $e")
        }
    }

    override suspend fun getDoors() {
        try {
            val response = apiService.getDoors().execute()
            Log.e("DEBUG1", "resp -> $response")
            response.body()?.let {
                Log.e("DEBUG1", "body -> $it")
            }
        } catch (e: Exception) {
            Log.e("DEBUG1", "e -> $e")
        }
    }
}