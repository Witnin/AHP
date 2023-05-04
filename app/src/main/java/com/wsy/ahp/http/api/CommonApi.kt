package com.wsy.ahp.http.api


import com.wsy.ahp.http.common.NetUrl
import com.wsy.ahp.model.entity.*
import retrofit2.Call
import retrofit2.http.Query

interface CommonApi {


    @retrofit2.http.GET(NetUrl.upload)
    fun upload()
            : Call<VersionService>


}