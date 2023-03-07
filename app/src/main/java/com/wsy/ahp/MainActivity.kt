package com.wsy.ahp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import com.wsy.ahp.coroutine.CoroutineScene.startScene1
import com.wsy.ahp.coroutine.CoroutineScene3
import com.wsy.ahp.coroutine.CountDownLatchDemo
import com.wsy.ahp.logic.MainActivityLogic
import com.wsy.ahp.logic.MainActivityLogic.ActivityProvider
import com.wsy.ahp.thread.*
import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : HiBaseActivity(), ActivityProvider {
    private var activityLogic: MainActivityLogic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityLogic = MainActivityLogic(this, savedInstanceState)

        ActivityManager.instance.addFrontBackCallback(object :ActivityManager.FrontBackCallback{
            override fun onChanged(front: Boolean) {
                Toast.makeText(applicationContext, "当前处于：$front",Toast.LENGTH_LONG).show()
            }
        })


//        ReentrantReadWriteLockDemo.test()
        CountDownLatchDemo.test2()
    }
}