package com.wsy.ahp.activity

import android.app.Activity
import com.wsy.common.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.activity_test.*

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