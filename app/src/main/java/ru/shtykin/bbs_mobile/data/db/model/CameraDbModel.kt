package ru.shtykin.bbs_mobile.data.db.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CameraDbModel(): RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var camera_id: Int = -1
    var name: String = ""
    var room: String = ""
    var snapshot: String = ""
    var favorites: Boolean = false
    var rec: Boolean = false
}