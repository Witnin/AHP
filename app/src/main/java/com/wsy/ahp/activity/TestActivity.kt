package com.wsy.ahp.activity

import android.app.Activity

import com.wsy.common.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener
import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils
import com.google.android.material.internal.ContextUtils.getActivity
import com.wsy.ahp.BuildConfig
import com.wsy.ahp.R
import com.wsy.ahp.http.api.CommonApi

import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.VersionService
import com.wsy.ahp.route.RouteFlag
import com.xuexiang.xupdate.XUpdate
import constant.UiType.CUSTOM
import constant.UiType.PLENTIFUL
import constant.UiType.SIMPLE
import kotlinx.android.synthetic.main.activity_test.*
import listener.UpdateDownloadListener
import me.reezy.cosmo.update.UpdateInfo
import me.reezy.cosmo.update.UpdateManager
import model.UiConfig
import model.UpdateConfig

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import update.UpdateAppUtils
import java.time.LocalDateTime


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
                        val datetime = LocalDateTime.now()
//                        // 更新配置
                        val updateConfig = UpdateConfig().apply {
                            force = true
                            checkWifi = true
                            needCheckMd5 = false
                            isShowNotification = true
                            notifyImgRes = R.drawable.kje
                            apkSavePath = Environment.getDataDirectory().absolutePath +"/aph"
                            apkSaveName = "aph$datetime"
                        }

                        if (versionCode<versionRes){
                            //方式一
//                            UpdateAppUtils
//                                .getInstance()
//                                .apkUrl(apkUrl)
//                                .updateTitle("发现新版本V$version")
//                                .updateContent(remarks)
////                                .updateConfig(updateConfig)
//                                .uiConfig(UiConfig(uiType = PLENTIFUL))
//                                .update()
                            //---------------------------------------------
                            //第二种方式，使用MODEL方式，组装好对应的MODEL，传入sdk中
                            var info =  DownloadInfo().setApkUrl(apkUrl)
                                .setFileSize(31338250)
                                .setProdVersionCode(versionRes)
                                .setProdVersionName(version)
//                                .setMd5Check("68919BF998C29DA3F5BD2C0346281AC0")
//                                .setForceUpdateFlag(listModel.isForceUpdate() ? 1 : 0)
                            .setUpdateLog(remarks);
                            AppUpdateUtils.getInstance()
//                                .addMd5CheckListener(...)//添加MD5检查更新
//                            .addAppDownloadListener()//添加文件下载监听
                            .checkUpdate(info);

                            //--------------------------------------------------
                            //方法三
//                            UpdateManager.setChecker {
//                                UpdateInfo(
//                                    hasUpdate = true,
//                                    isIgnorable = false,
//                                    updateContent = remarks,
//                                    url = apkUrl,
//                                )
//                            }
//
//                            UpdateManager.check(this@TestActivity)

                            //--------------------------------------------------


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