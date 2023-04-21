package com.wsy.wsy_library.cache

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object HiStorage {
    fun deleteCache(key: String) {
        val cache = Cache()
        cache.key = key
        CacheDatabase.get().getCacheDao.deleteCache(cache)
    }

    fun <T> saveCache(key: String, body: T) {
        val cache = Cache()
        cache.key = key
        cache.data = toByteArray(body)
        CacheDatabase.get().getCacheDao.saveCache(cache)

    }


    fun <T> getCache(key: String): T? {
        val cache: Cache? = CacheDatabase.get().getCacheDao.getCache(key)
        return (if (cache?.data != null) {
            toObject(cache.data)
        } else null) as? T?
    }

    //序列化存储数据需要转换成二进制
    private fun <T> toByteArray(body: T): ByteArray? {
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(body)
            oos.flush()
        } catch (
            e: java.lang.Exception

        ) {
            e.printStackTrace()
        } finally {
            baos?.close()
            oos?.close()
        }

        return ByteArray(0)
    }

    //反序列,把二进制数据转换成java object对象
    private fun toObject(data: ByteArray?): Any? {
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bais = ByteArrayInputStream(data)
            ois = ObjectInputStream(bais)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            bais?.close()
            ois?.close()

        }
        return null
    }

}