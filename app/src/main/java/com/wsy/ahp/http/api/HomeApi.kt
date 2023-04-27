package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import com.wsy.ahp.http.common.NetUrl
import com.wsy.ahp.model.entity.CommonService
import com.wsy.ahp.model.entity.LoginService
import com.wsy.ahp.model.entity.TypeService
import com.wsy.wsy_library.restful.HiCall
import com.wsy.wsy_library.restful.annotation.GET
import com.wsy.wsy_library.restful.annotation.Filed
import com.wsy.wsy_library.restful.annotation.POST
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Query


interface HomeApi {


    @retrofit2.http.GET(NetUrl.type)
    fun type(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int)
            : Call<TypeService>
}