package com.wsy.ahp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.animation.AnimatorSetCompat.playTogether
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
import java.lang.Integer.max
import java.time.Clock
import java.time.Instant
import java.time.temporal.ChronoUnit


class MainActivity : HiBaseActivity(), ActivityProvider {
    private var activityLogic: MainActivityLogic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        先调用installSplashScreen，然后才能调用setContentView
        val splashScreen = installSplashScreen()

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