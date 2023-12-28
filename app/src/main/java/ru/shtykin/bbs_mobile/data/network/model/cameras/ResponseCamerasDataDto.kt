package ru.shtykin.bbs_mobile.data.network.model.cameras

import com.google.gson.annotations.SerializedName

data class ResponseCamerasDataDto(
    @SerializedName("room") val room: List<String>,
    @SerializedName("cameras") val cameras: List<ResponseCamerasItemDto>,

    )
