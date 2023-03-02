package com.wsy.ahp.activity

import android.app.Activity
import com.wsy.common.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.route.RouteFlag
import kotlinx.android.synthetic.main.activity_test.*

@Route(path = "/vip/detail", extras = RouteFlag.FLAG_VIP)
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var topActivity: Activity? = ActivityManager.instance.topActivity
        if(topActivity!=null){
            jumpTest.setText("当前页面：${topActivity.localClassName}")
        }
    }
}