package com.wsy.ahp



import android.util.Log
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.amap.api.maps.MapsInitializer
import com.amap.api.services.core.ServiceSettings
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig
import com.cretin.www.cretinautoupdatelibrary.model.UpdateConfig
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils
import com.wsy.common.ActivityManager
import com.wsy.common.ui.component.HiBaseApplication
import com.yhao.floatwindow.FloatWindow
import com.yhao.floatwindow.MoveType
import com.yhao.floatwindow.PermissionListener
import com.yhao.floatwindow.Screen
import com.yhao.floatwindow.ViewStateListener





class HiApplication: HiBaseApplication() {

    val TAG = "HiApplication"
    override fun onCreate() {
        super.onCreate()
        ActivityManager.instance.init(this)

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG){
            ARouter.openLog() // 打印日志
            ARouter.openDebug() // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化
        //方法一
//        UpdateAppUtils.init(applicationContext)


        //方法二
        //如果你想使用okhttp作为下载的载体，那么你需要自己依赖okhttp，更新库不强制依赖okhttp！可以使用如下代码创建一个OkHttpClient 并在UpdateConfig中配置setCustomDownloadConnectionCreator start
        //如果你想使用okhttp作为下载的载体，那么你需要自己依赖okhttp，更新库不强制依赖okhttp！可以使用如下代码创建一个OkHttpClient 并在UpdateConfig中配置setCustomDownloadConnectionCreator start
//        val builder = OkHttpClient.Builder()
//        builder.connectTimeout(30000, TimeUnit.SECONDS)
//            .readTimeout(30000, TimeUnit.SECONDS)
//            .writeTimeout(30000, TimeUnit.SECONDS) //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 start
//            .sslSocketFactory(SSLUtils.createSSLSocketFactory())
//            .hostnameVerifier(SSLUtils.TrustAllHostnameVerifier()) //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 end
//            .retryOnConnectionFailure(true)
        //当你希望使用传入model的方式，让插件自己解析并实现更新
        val updateConfig: UpdateConfig = UpdateConfig()
            .setDebug(false) //是否是Debug模式
            .setDataSourceType(TypeConfig.DATA_SOURCE_TYPE_MODEL) //设置获取更新信息的方式
            .setShowNotification(true) //配置更新的过程中是否在通知栏显示进度
            .setNotificationIconRes(R.drawable.kje) //配置通知栏显示的图标
            .setUiThemeType(TypeConfig.UI_THEME_G) //配置UI的样式，一种有12种样式可供选择
            .setAutoDownloadBackground(false) //是否需要后台静默下载，如果设置为true，则调用checkUpdate方法之后会直接下载安装，不会弹出更新页面。当你选择UI样式为TypeConfig.UI_THEME_CUSTOM，静默安装失效，您需要在自定义的Activity中自主实现静默下载，使用这种方式的时候建议setShowNotification(false)，这样基本上用户就会对下载无感知了
//            .setCustomActivityClass(CustomActivity::class.java) //如果你选择的UI样式为TypeConfig.UI_THEME_CUSTOM，那么你需要自定义一个Activity继承自RootActivity，并参照demo实现功能，在此处填写自定义Activity的class
            .setNeedFileMD5Check(false) //是否需要进行文件的MD5检验，如果开启需要提供文件本身正确的MD5校验码，DEMO中提供了获取文件MD5检验码的工具页面，也提供了加密工具类Md5Utils
//            .setCustomDownloadConnectionCreator(OkHttp3Connection.Creator(builder)) //如果你想使用okhttp作为下载的载体，可以使用如下代码创建一个OkHttpClient，并使用demo中提供的OkHttp3Connection构建一个ConnectionCreator传入，在这里可以配置信任所有的证书，可解决根证书不被信任导致无法下载apk的问题

        AppUpdateUtils.init(this, updateConfig)
//---------------------------------
        //方法三
//        MMKV.initialize(applicationContext, if (BuildConfig.DEBUG) MMKVLogLevel.LevelDebug else MMKVLogLevel.LevelNone)


        //高德地图隐私合规
        //Map
        MapsInitializer.updatePrivacyShow(applicationContext, true, true)
        MapsInitializer.updatePrivacyAgree(applicationContext, true)

        //Search
        ServiceSettings.updatePrivacyShow(applicationContext,true,true);
        ServiceSettings.updatePrivacyAgree(applicationContext,true);

//        val sHA1 = Sha_Util.sHA1(applicationContext)
//        println("sHA1$sHA1")
//        Log.i("sha",sHA1)


        //悬浮球
        val imageView = ImageView(applicationContext)
        imageView.setImageResource(R.drawable.button_fl)



        val mPermissionListener: PermissionListener = object : PermissionListener {
            override fun onSuccess() {
                Log.d(TAG, "onSuccess")
            }

            override fun onFail() {
                Log.d(TAG, "onFail")
            }
        }

        val mViewStateListener: ViewStateListener = object : ViewStateListener {
            override fun onPositionUpdate(x: Int, y: Int) {
                Log.d(TAG, "onPositionUpdate: x=$x y=$y")
            }

            override fun onShow() {
                Log.d(TAG, "onShow")
            }

            override fun onHide() {
                Log.d(TAG, "onHide")
            }

            override fun onDismiss() {
                Log.d(TAG, "onDismiss")
            }

            override fun onMoveAnimStart() {
                Log.d(TAG, "onMoveAnimStart")
            }

            override fun onMoveAnimEnd() {
                Log.d(TAG, "onMoveAnimEnd")
            }

            override fun onBackToDesktop() {
                Log.d(TAG, "onBackToDesktop")
            }
        }

        FloatWindow
            .with(applicationContext)
            .setView(imageView)
            .setWidth(Screen.width, 0.05f) //设置悬浮控件宽高
            .setHeight(Screen.width, 0.05f)
            .setX(Screen.width, 0.95f)
            .setY(Screen.height, 0.3f)
            .setMoveType(MoveType.slide, 0, 0)
            .setMoveStyle(500, BounceInterpolator())
            .setViewStateListener(mViewStateListener)
            .setPermissionListener(mPermissionListener)
            .setDesktopShow(true)
            .build()

        imageView.setOnClickListener {
            Toast.makeText(
                this@HiApplication,
                "onClick",
                Toast.LENGTH_SHORT
            ).show()
        }

        imageView.setOnLongClickListener{
            Toast.makeText(
                this@HiApplication,
                "onLongClick",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

    }

    fun showToasts(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }


}