package com.wsy.ahp.fragment.nowarticle.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wsy.ahp.fragment.nowarticle.repository.VideoListRepository
import com.wsy.ahp.model.entity.Recommend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class VideoListDataSource(private val type: String, private val scope: CoroutineScope): PageKeyedDataSource<Int,Recommend>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Recommend>) {
        scope.launch {
            try {
                when (type) {
                    "水质" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize,  0)
                        callback.onResult(response.result.records,params.key+1)
                    }
                    "土壤" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize, 1)
                        callback.onResult(response.result.records,params.key+1)
                    }
                    "气体" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize,  2)
                        callback.onResult(response.result.records,params.key+1)

                    }
                    "食品" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize,  3)
                        callback.onResult(response.result.records,params.key+1)
                    }
                    "噪音" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize, 4)
                        callback.onResult(response.result.records,params.key+1)
                    }
                    "其他" -> {
                        val response = VideoListRepository.getRecommendList(params.key,params.requestedLoadSize,  5)
                        callback.onResult(response.result.records,params.key+1)
                    }

                }
            }catch (e: Exception) {
                Log.d("VideoListDataSource", "$e")
            }


        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Recommend>) {
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Recommend>
    ) {
        scope.launch {
            try {
                when (type) {
                    "水质" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize,  0)
                       callback.onResult(response.result.records,0,2)
                    }
                    "土壤" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize, 1)
                        callback.onResult(response.result.records,0,2)
                    }
                    "气体" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize,  2)
                        callback.onResult(response.result.records,0,2)

                    }
                    "食品" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize,  3)
                        callback.onResult(response.result.records,0,2)
                    }
                    "噪音" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize, 4)
                        callback.onResult(response.result.records,0,2)
                    }
                    "其他" -> {
                        val response = VideoListRepository.getRecommendList(1,params.requestedLoadSize,  5)
                        callback.onResult(response.result.records,0,2)
                    }

                }
            }catch (e: Exception) {
                Log.d("VideoListDataSource", "$e")
            }


        }
    }


}