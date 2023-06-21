package com.wsy.common.utils

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

object StatusBarUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun setStatusBar(activity: Activity, barType:Int, colorIds:Int){
        //设置首页和其他页面的bar样式
//        if(barType==HOME_BAR){
//            activity.window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//        }else{
            activity.window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        }
        //设置颜色
        activity.window.statusBarColor= ContextCompat.getColor(activity,colorIds)
    }
}