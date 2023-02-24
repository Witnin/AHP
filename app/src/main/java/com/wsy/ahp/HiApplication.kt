package com.wsy.ahp


import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseApplication

class HiApplication: HiBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        ActivityManager.instance.init(this)
    }
}