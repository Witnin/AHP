package com.wsy.ahp.http.api


import com.wsy.ahp.http.common.NetUrl
import com.wsy.ahp.model.entity.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonApi {


    @retrofit2.http.GET(NetUrl.versionInfo)
    fun upload()
            : Call<VersionService>

    @Multipart
    @POST(NetUrl.upload)
    fun uploadImage(@Part file:MultipartBody.Part): Call<UploadService>


}