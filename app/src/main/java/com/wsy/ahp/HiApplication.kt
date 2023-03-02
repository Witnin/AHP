package com.wsy.ahp


import android.util.Config
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseApplication

class HiApplication: HiBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        ActivityManager.instance.init(this)

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG){
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化




    }


}