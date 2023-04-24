package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import com.wsy.wsy_library.restful.HiCall
import com.wsy.wsy_library.restful.annotation.GET
import com.wsy.wsy_library.restful.annotation.Filed
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Query


interface TestApi {

    @GET("recommend/gyssEverydayRecommend/list")
    fun recommendList(@Filed("pageNo") pageNo:Int, @Filed("pageNo") pageSize:Int) : HiCall<JsonObject>

    @retrofit2.http.GET("recommend/gyssEverydayRecommend/list")
    fun recommend(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int) : Call<JsonObject>
}