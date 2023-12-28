package ru.shtykin.bbs_mobile.data.network.model.doors

import com.google.gson.annotations.SerializedName

data class ResponseDoorsDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: List<ResponseDoorsDataDto>,
)
