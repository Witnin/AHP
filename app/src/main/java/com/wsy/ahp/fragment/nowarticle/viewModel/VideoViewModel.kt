package com.wsy.ahp.fragment.nowarticle.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.wsy.ahp.fragment.nowarticle.datasource.VideoListDataSourceFactory


import com.wsy.ahp.model.entity.Recommend



class VideoViewModel(private val type:String) :ViewModel(){
//    // 1
//    private val _videoList = MutableLiveData<List<Recommend>>()
//    // 2
//    val videoList: LiveData<List<Recommend>>
//        get() = _videoList
//    // 3

    var pagedListLiveData = LivePagedListBuilder<Int, Recommend>(
        VideoListDataSourceFactory(type, viewModelScope),
        PagedList.Config.Builder().setPageSize(18).build()
    ).build()


    /* 下拉刷新 */
    fun resetQuery() {
        pagedListLiveData.value?.dataSource?.invalidate()
    }
}