package com.wsy.ahp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.wsy.ahp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //隐藏导航栏
        //隐藏导航栏
        val actionbar = supportActionBar
        actionbar?.hide()
    }


}