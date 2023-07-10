package com.wsy.ahp.fragment.nowarticle.api

import android.util.Log
import com.wsy.ahp.http.ApiFactory
import com.wsy.ahp.http.common.NetUrl
import com.wsy.ahp.model.entity.RecommendService
import com.wsy.ahp.model.entity.TypeService
import com.wsy.common.utils.SPUtil
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApiService {

    companion object {
        private const val TAG = "ApiService"

        val token = SPUtil.getString("X-Access-Token") ?: ""

        // 1
        fun create(): VideoApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiFactory.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(VideoApiService::class.java)
        }
        // 2
        private val okHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                        chain ->
                    val request: Request = chain.request()
                    val build: Request = request.newBuilder()
                        .addHeader("X-Access-Token", token)
                        .build()
                    chain.proceed(build)
                }
                .build()
        // 3
        private val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor(object :
                    HttpLoggingInterceptor.Logger{
                    override fun log(message: String) {


                    Log.i(TAG, message)
                }
            })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }
    }

    @GET(NetUrl.type)
    suspend fun getType(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int) :
            TypeService

    @GET(NetUrl.article_list)
    suspend fun getRecommendList(@Query("pageNo") pageNo:Int, @Query("pageSize") pageSize:Int, @Query("recSort") recSort:Int) :
            RecommendService




}