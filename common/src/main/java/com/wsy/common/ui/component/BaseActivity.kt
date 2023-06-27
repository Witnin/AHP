package com.wsy.common.ui.component

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.wsy.common.R
import com.wsy.common.utils.StatusBarUtils


abstract class BaseActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())


        init()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initData()

    protected abstract fun init()


    override fun onDestroy() {
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setLightStatusBar(){
        StatusBarUtils.setStatusBar(this, View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR, R.color.color_298)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setTransStatusBar(){
        StatusBarUtils.setStatusBar(this,View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR , com.wsy.ui.R.color.white)
    }
}