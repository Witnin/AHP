package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import com.wsy.ahp.model.entity.LoginService
import com.wsy.wsy_library.restful.HiCall
import com.wsy.wsy_library.restful.annotation.GET
import com.wsy.wsy_library.restful.annotation.Filed
import com.wsy.wsy_library.restful.annotation.POST
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field


interface LoginApi {

    @POST("sys/mLogin")
    fun login(@Filed("username") username:String,
              @Filed("password") password:String)
    : HiCall<JsonObject>



    @retrofit2.http.POST("sys/mLogin")
    fun mLogin(@Body body: RequestBody)
            : Call<JsonObject>

    @retrofit2.http.POST("sys/mLogin")
    fun sLogin(@Body body: RequestBody)
            : Call<LoginService>
}