package com.wsy.ahp.http

import android.text.TextUtils
import com.wsy.wsy_library.log.HiLog
import com.wsy.wsy_library.restful.HiInterceptor
import com.wsy.wsy_library.restful.HiRequest

/**
 * 拦截器添加全局header
 */
class BizInterceptor : HiInterceptor {
    override fun intercept(chain: HiInterceptor.Chain): Boolean {
        val request = chain.request()
        val response = chain.response()
        if (chain.isRequestPeriod) {
//            val boardingPass = SPUtil.getString("boarding-pass") ?: ""
//            request.addHeader("boarding-pass", boardingPass)
            request.addHeader("X-Access-Token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODIwNjU1NjIsInVzZXJuYW1lIjoicGVyc29uMjIifQ.o5SNGNC8sU89naM4f1XWTW4Iwzw3lETbuvYRwCvCAwc")
        } else if (response != null) {
            var outputBuilder = StringBuilder()
            val httpMethod: String =
                if (request.httpMethod == HiRequest.METHOD.GET)
                    "GET"
                else if (request.httpMethod == HiRequest.METHOD.POST)
                    "POST"
                else
                    "PUT"
            val requestUrl: String = request.endPointUrl()
            outputBuilder.append("\n$requestUrl==>$httpMethod\n")


            if (request.headers != null) {
                outputBuilder.append("【headers\n")
                request.headers!!.forEach(action = {
                    outputBuilder.append(it.key + ":" + it.value)
                    outputBuilder.append("\n")
                })
                outputBuilder.append("headers】\n")
            }

            if (request.parameters != null && request.parameters!!.isNotEmpty()) {
                outputBuilder.append("【parameters==>\n")
                request.parameters!!.forEach(action = {
                    outputBuilder.append(it.key + ":" + it.value + "\n")
                })
                outputBuilder.append("parameters】\n")
            }

            outputBuilder.append("【response==>\n")
            outputBuilder.append(response.rawData + "\n")
            outputBuilder.append("response】\n")

            HiLog.dt("BizInterceptor Http:", chain.request().endPointUrl())
            HiLog.dt("BizInterceptor Http:", chain.response()!!.rawData)
//            HiLog.dt("BizInterceptor Http:", outputBuilder.toString())
        }

        return false
    }

}