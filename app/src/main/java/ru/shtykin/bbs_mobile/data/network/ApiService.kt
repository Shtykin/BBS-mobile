package ru.shtykin.bbs_mobile.data.network

import retrofit2.Call
import retrofit2.http.GET
import ru.shtykin.bbs_mobile.data.network.model.cameras.ResponseCamerasDto
import ru.shtykin.bbs_mobile.data.network.model.doors.ResponseDoorsDto

interface ApiService {

    @GET("/api/rubetek/cameras/")
    fun getCameras (
    ): Call<ResponseCamerasDto>

    @GET("/api/rubetek/doors/")
    fun getDoors (
    ): Call<ResponseDoorsDto>
}