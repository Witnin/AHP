package com.wsy.ahp.fragment.nowarticle.repository

import com.wsy.ahp.fragment.nowarticle.api.VideoApiService
import com.wsy.ahp.model.entity.RecommendService

object VideoListRepository {

    suspend fun getRecommendList(pageNo:Int,PageSize:Int,recSort:Int): RecommendService{
        return VideoApiService.create().getRecommendList(pageNo, PageSize,recSort)
    }
}