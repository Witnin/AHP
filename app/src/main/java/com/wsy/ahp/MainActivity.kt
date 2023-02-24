package com.wsy.ahp

import android.os.Bundle
import android.widget.Toast
import com.wsy.ahp.logic.MainActivityLogic
import com.wsy.ahp.logic.MainActivityLogic.ActivityProvider
import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseActivity

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
    }
}