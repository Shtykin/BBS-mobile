package ru.shtykin.bbs_mobile.domain.entity

data class Camera(
    val id: Int,
    val name: String,
    val room: String?,
    val snapshot: String,
    val favorites: Boolean,
    val rec: Boolean,
)
