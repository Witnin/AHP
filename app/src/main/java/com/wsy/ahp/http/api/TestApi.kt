package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Query


interface TestApi {



    @retrofit2.http.GET("recommend/gyssEverydayRecommend/list")
    fun recommend(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int) : Call<JsonObject>
}