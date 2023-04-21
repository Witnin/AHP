package com.wsy.ahp.http.api

import com.google.gson.JsonObject
import com.wsy.wsy_library.restful.HiCall
import com.wsy.wsy_library.restful.annotation.GET
import com.wsy.wsy_library.restful.annotation.Filed



interface TestApi {

    @GET("recommend/gyssEverydayRecommend/list")
    fun recommendList(@Filed("pageNo") pageNo:Int, @Filed("pageNo") pageSize:Int) : HiCall<JsonObject>
}