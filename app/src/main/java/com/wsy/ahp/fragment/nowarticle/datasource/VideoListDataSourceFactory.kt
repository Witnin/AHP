package com.wsy.ahp.fragment.nowarticle.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wsy.ahp.model.entity.Recommend
import com.wsy.ahp.view.LoadingStatus
import kotlinx.coroutines.CoroutineScope

class VideoListDataSourceFactory(private val type: String, private val scope: CoroutineScope,private val loadingStatusLiveData: MutableLiveData<LoadingStatus>) : DataSource.Factory<Int, Recommend>() {
    override fun create(): DataSource<Int, Recommend> {
        return VideoListDataSource(type, scope,loadingStatusLiveData)
    }
}