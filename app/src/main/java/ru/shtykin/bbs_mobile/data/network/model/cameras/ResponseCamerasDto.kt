package ru.shtykin.bbs_mobile.data.network.model.cameras

import com.google.gson.annotations.SerializedName

data class ResponseCamerasDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: ResponseCamerasDataDto,
)
