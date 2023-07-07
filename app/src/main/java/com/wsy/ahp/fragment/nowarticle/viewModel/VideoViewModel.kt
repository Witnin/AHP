package com.wsy.ahp.fragment.nowarticle.viewModel
import android.annotation.SuppressLint
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.wsy.ahp.fragment.nowarticle.repository.VideoListRepository
import com.wsy.ahp.model.entity.Recommend

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VideoViewModel :ViewModel(){
    // 1
    private val _videoList = MutableLiveData<List<Recommend>>()
    // 2
    val videoList: LiveData<List<Recommend>>
        get() = _videoList
    // 3
    var type: String = ""

    private val myJob = Job()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i("请求错误信息", "${throwable.message}")
    }
    private val myScope = CoroutineScope(myJob + Dispatchers.Main + exceptionHandler)

    @SuppressLint("NotifyDataSetChanged")
    fun requestData(){
        myScope.launch {
            when(type){
                "水质"->{
                    val response = VideoListRepository.getRecommendList(1,20,0)
                    _videoList.value = response.result.records
                }
                "土壤"->{
                    val response = VideoListRepository.getRecommendList(1, 20,1)
                    _videoList.value = response.result.records
                }
                "气体"->{
                    val response = VideoListRepository.getRecommendList(1, 20,2)
                    _videoList.value = response.result.records

                }
                "食品"->{
                    val response = VideoListRepository.getRecommendList(1, 20,3)
                    _videoList.value = response.result.records
                }
                "噪音"->{
                    val response = VideoListRepository.getRecommendList(1, 20,4)
                    _videoList.value = response.result.records
                }
                "其他"->{
                    val response = VideoListRepository.getRecommendList(1, 20,5)
                    _videoList.value = response.result.records
                }

            }

        }
    }
}