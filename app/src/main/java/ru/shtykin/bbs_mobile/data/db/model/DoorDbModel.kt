package ru.shtykin.bbs_mobile.data.db.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class DoorDbModel(): RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var door_id: Int = -1
    var name: String = ""
    var room: String = ""
    var snapshot: String = ""
    var favorites: Boolean = false
}