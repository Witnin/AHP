package com.wsy.ahp.http.api

import com.wsy.ahp.http.common.NetUrl
import com.wsy.ahp.model.entity.RecommendService
import com.wsy.ahp.model.entity.TypeService
import retrofit2.Call
import retrofit2.http.Query


interface HomeApi {


    @retrofit2.http.GET(NetUrl.type)
    fun type(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int)
            : Call<TypeService>


    @retrofit2.http.GET("recommend/gyssEverydayRecommend/smallListWithComment")
    fun recommend(@Query("pageNo") pageNo:Int,
                  @Query("pageSize") pageSize:Int,
                  @Query("recSort") recSort:Int,

    ) : Call<RecommendService>
}