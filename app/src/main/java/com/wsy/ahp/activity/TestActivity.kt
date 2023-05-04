package com.wsy.ahp.activity

import android.app.Activity
import com.wsy.common.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.BuildConfig
import com.wsy.ahp.R
import com.wsy.ahp.http.api.CommonApi

import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.VersionService
import com.wsy.ahp.route.RouteFlag
import constant.UiType.CUSTOM
import constant.UiType.PLENTIFUL
import kotlinx.android.synthetic.main.activity_test.*
import model.UiConfig

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import update.UpdateAppUtils


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

        versionInfo()




    }

    private fun versionInfo(){
        val commonApi = RetrofitServiceCreator.create(CommonApi::class.java)
        commonApi.upload().enqueue(object : Callback<VersionService>{
            override fun onResponse(
                call: Call<VersionService>,
                response: Response<VersionService>
            ) {
                if(response.isSuccessful){
                    if (response.body()!!.code == 200){
                        val data = response.body()!!.result
                        Log.i("version",data.toString())
                        val version = data.sysVersion
                        Log.i("version",version)
                        val apkUrl = data.sysValue
                        val remarks = data.sysRemarks
                        val versionRes = version.replace(".","").toInt()
                        val versionCode = BuildConfig.VERSION_CODE
                        if (versionCode<versionRes){
                            UpdateAppUtils
                                .getInstance()
                                .apkUrl(apkUrl)
                                .updateTitle("发现新版本V$version")
                                .updateContent(remarks)
                                .uiConfig(UiConfig(uiType = CUSTOM))
                                .update()
                        }else{
                            showToasts(getString(R.string.get_failed)+response.message())
                        }
                    }else{
                        showToasts(getString(R.string.get_failed)+response.message()+"000")
                    }
                }else{
                    showToasts(getString(R.string.get_failed)+response.message()+"111")
                }
            }

            override fun onFailure(call: Call<VersionService>, t: Throwable) {
                showToasts(getString(R.string.get_failed)+"222")
            }

        })
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }

    fun showToasts(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }


}