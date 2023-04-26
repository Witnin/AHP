package com.wsy.ahp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import com.google.gson.JsonObject
import com.wsy.ahp.coroutine.CoroutineScene.startScene1
import com.wsy.ahp.coroutine.CoroutineScene3
import com.wsy.ahp.coroutine.CountDownLatchDemo
import com.wsy.ahp.http.ApiFactory
import com.wsy.ahp.http.api.TestApi
import com.wsy.ahp.logic.MainActivityLogic
import com.wsy.ahp.logic.MainActivityLogic.ActivityProvider
import com.wsy.ahp.thread.*
import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseActivity
import com.wsy.wsy_library.restful.HiCallback
import com.wsy.wsy_library.restful.HiResponse
import com.wsy.wsy_library.util.HiStatusBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.http.Field

class MainActivity : HiBaseActivity(), ActivityProvider {
    private var activityLogic: MainActivityLogic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityLogic = MainActivityLogic(this, savedInstanceState)

        HiStatusBar.setStatusBar(this,true, Color.WHITE,false)

        ActivityManager.instance.addFrontBackCallback(object :ActivityManager.FrontBackCallback{
            override fun onChanged(front: Boolean) {
                Toast.makeText(applicationContext, "当前处于：$front",Toast.LENGTH_LONG).show()
            }
        })


//        ReentrantReadWriteLockDemo.test()
//        CountDownLatchDemo.test2()
//        ApiFactory.create(TestApi::class.java).recommendList(1,10).enqueue(object: HiCallback<JsonObject>{
//            override fun onSuccess(response: HiResponse<JsonObject>) {
//
//            }
//
//            override fun onFailed(throwable: Throwable) {
//
//            }
//
//        })


    }
}