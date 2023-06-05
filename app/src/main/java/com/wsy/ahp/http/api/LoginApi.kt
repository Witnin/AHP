package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import com.wsy.ahp.model.entity.CommonService
import com.wsy.ahp.model.entity.LoginService
import com.wsy.wsy_library.restful.HiCall
import com.wsy.wsy_library.restful.annotation.WsyFiled
import com.wsy.wsy_library.restful.annotation.WSY_POST
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Query


interface LoginApi {

    @WSY_POST("sys/mLogin")
    fun login(@WsyFiled("username") username:String,
              @WsyFiled("password") password:String)
    : HiCall<JsonObject>


    @retrofit2.http.POST("sys/mLogin")
    fun mLogin(@Body body: RequestBody)
            : Call<JsonObject>

    @retrofit2.http.POST("sys/mLogin")
    fun sLogin(@Body body: RequestBody)
            : Call<LoginService>

    @retrofit2.http.GET("banner/gyssProjectBanner/list")
    fun banner(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int)
            : Call<CommonService>
}