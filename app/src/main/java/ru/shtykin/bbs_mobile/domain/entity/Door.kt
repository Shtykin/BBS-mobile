package ru.shtykin.bbs_mobile.domain.entity

data class Door(
    val id: Int,
    val name: String,
    val room: String?,
    val snapshot: String?,
    val favorites: Boolean,
)
