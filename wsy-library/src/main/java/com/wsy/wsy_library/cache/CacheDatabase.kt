package com.wsy.wsy_library.cache

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wsy.wsy_library.util.AppGlobals

@Database(
    entities = [Cache::class],
    version = 1,
    exportSchema = false
)
abstract class CacheDatabase : RoomDatabase() {
    companion object {
        private var database: CacheDatabase

        fun get(): CacheDatabase {
            return database
        }

        init {
            database = Room.databaseBuilder(
                AppGlobals.get()!!.applicationContext, CacheDatabase::class.java, "ahp_db"
            ).build()
        }
    }

    //获取操作数据库数据的dao对象
    abstract val getCacheDao:CacheDao
}