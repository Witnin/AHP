package com.wsy.ahp.route

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor


@Interceptor(name = "biz_interceptor", priority = 9)
class BizInterceptor: IInterceptor {
    private var context: Context? = null
    override fun init(context: Context?) {
        this.context = context
    }


    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val flag: Int = postcard!!.extra

        if ((flag and (RouteFlag.FLAG_LOGIN) != 0)) {
            //login
            callback!!.onInterrupt(RuntimeException("need login"))
            showToast("请先登录")
        } else if ((flag and (RouteFlag.FLAG_AUTHENTICATION) != 0)) {
            //authentication()
            callback!!.onInterrupt(RuntimeException("need authentication"))
            showToast("请先实名认证")
        } else if ((flag and (RouteFlag.FLAG_VIP) != 0)) {
            //vip
            callback!!.onInterrupt(RuntimeException("need become vip"))
            showToast("请先注册会员")
        } else{
            callback!!.onContinue(postcard)
        }
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }

}