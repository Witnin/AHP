package com.wsy.ahp.http

import com.wsy.wsy_library.restful.HiRestful

object ApiFactory {
    val baseUrl = "https://nanputech.com:8091/"
    private val hiRestful:HiRestful = HiRestful(baseUrl,RetrofitCallFactory(baseUrl))
//    val KEY_DEGRADE_HTTP = "degrade_http"
//    val HTTPS_BASE_URL = "https://api.devio.org/as/"
//    val HTTP_BASE_URL = "http://api.devio.org/as/"
//    val degrade2Http = SPUtil.getBoolean(KEY_DEGRADE_HTTP)
//    val baseUrl = if (degrade2Http) HTTP_BASE_URL else HTTPS_BASE_URL

//
    init {
        hiRestful.addInterceptor(BizInterceptor())
//        hiRestful.addInterceptor(HttpStatusInterceptor())

//        SPUtil.putBoolean(KEY_DEGRADE_HTTP,false)
    }

    fun <T> create(service: Class<T>): T {
        return hiRestful.create(service)
    }
}