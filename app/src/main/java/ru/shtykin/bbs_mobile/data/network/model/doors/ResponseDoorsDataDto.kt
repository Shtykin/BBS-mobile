package ru.shtykin.bbs_mobile.data.network.model.doors

import com.google.gson.annotations.SerializedName

data class ResponseDoorsDataDto(
    @SerializedName("name") val name: String,
    @SerializedName("snapshot") val snapshot: String,
    @SerializedName("room") val room: String,
    @SerializedName("id") val id: Int,
    @SerializedName("favorites") val favorites: Boolean,
    )
