package com.wsy.ahp.fragment.nowarticle.datasource

import androidx.paging.DataSource
import com.wsy.ahp.model.entity.Recommend
import kotlinx.coroutines.CoroutineScope

class VideoListDataSourceFactory(private val type: String, private val scope: CoroutineScope) : DataSource.Factory<Int, Recommend>() {
    override fun create(): DataSource<Int, Recommend> {
        return VideoListDataSource(type, scope)
    }
}