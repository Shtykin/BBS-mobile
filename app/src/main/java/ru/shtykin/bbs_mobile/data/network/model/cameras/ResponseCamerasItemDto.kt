package ru.shtykin.bbs_mobile.data.network.model.cameras

import com.google.gson.annotations.SerializedName

data class ResponseCamerasItemDto(
    @SerializedName("name") val name: String,
    @SerializedName("snapshot") val snapshot: String,
    @SerializedName("room") val room: String,
    @SerializedName("id") val id: Int,
    @SerializedName("favorites") val favorites: Boolean,
    @SerializedName("rec") val rec: Boolean,
)
