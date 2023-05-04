package com.wsy.ahp.activity

import android.app.Activity
import com.wsy.common.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.BuildConfig
import com.wsy.ahp.R
import com.wsy.ahp.route.RouteFlag
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test.item_update
import kotlinx.android.synthetic.main.fragment_profile.*
import me.reezy.cosmo.update.UpdateManager

@Route(path = "/vip/detail", extras = RouteFlag.FLAG_VIP)
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        /**
         *         BuildConfig.VERSION_CODE
         * */
        var topActivity: Activity? = ActivityManager.instance.topActivity
        var versionName = packageManager.getPackageInfo(packageName,0).versionName
        var versionCode = packageManager.getPackageInfo(packageName,0).versionCode
        var appVersion = resources.getInteger(R.integer.version_code)
        var appName = resources.getString(R.string.version_name)


        if(topActivity!=null){
            jumpTest.setText("当前页面：${topActivity.localClassName},gradle版本名称：" +
                    "$versionName,gradle版本code:$versionCode,,manifest版本名称：$appName,manifest版本code:$appVersion")
        }




    }




}