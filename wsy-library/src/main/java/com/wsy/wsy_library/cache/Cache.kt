package com.wsy.wsy_library.cache

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ahp_cache")
class Cache {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key:String = ""

    var data:ByteArray? = null
}