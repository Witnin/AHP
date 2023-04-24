package com.wsy.ahp.http.common

import android.accessibilityservice.GestureDescription
import com.wsy.ahp.http.ApiFactory
import com.wsy.common.utils.SPUtil
import okhttp3.Cookie
import okhttp3.Headers.Builder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceCreator {
    val token = SPUtil.getString("X-Access-Token") ?: ""
    var builder: OkHttpClient.Builder =OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor { chain ->

            val request: Request = chain.request()
            val build: Request = request.newBuilder()
                .addHeader("X-Access-Token", token)
                .build()
            chain.proceed(build)
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiFactory.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(builder.build())
        .build()

    fun <T> create (serviceClass: Class<T>) : T = retrofit.create(serviceClass)

}