package com.wsy.ahp.activity.banner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.app.hubert.guide.NewbieGuide
import com.app.hubert.guide.model.GuidePage
import com.bumptech.glide.Glide
import com.dalimao.library.util.FloatUtil
import com.wsy.ahp.R
import com.wsy.ui.banner.HiBanner
import com.wsy.ui.banner.core.HiBannerMo
import com.wsy.ui.banner.indicator.HiCircleIndicator
import com.wsy.ui.banner.indicator.HiIndicator
import com.wsy.ui.banner.indicator.HiNumIndicator
import com.wsy.ui.floate.SimpleView


class HiBannerDemoActivity : AppCompatActivity() {
    private var urls = arrayOf(
        "https://www.devio.org/img/beauty_camera/beauty_camera1.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera3.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera4.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera5.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera2.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera6.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera7.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera8.jpeg"
    )
    private var hiIndicator: HiIndicator<*>? = null
    private var autoPlay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_banner_demo)
        initView(HiCircleIndicator(this), false)
        findViewById<Switch>(R.id.auto_play).setOnCheckedChangeListener { _, isChecked ->
            autoPlay = isChecked
            initView(hiIndicator, autoPlay)
        }
        findViewById<View>(R.id.tv_switch).setOnClickListener {
            //            mHiBanner.setAutoPlay(false)
            if (hiIndicator is HiCircleIndicator) {
                initView(HiNumIndicator(this), autoPlay)
            } else {
                initView(HiCircleIndicator(this), autoPlay)
            }

        }


        val btnSimple = findViewById<View>(R.id.viewButton) as Button
        val floatBtn = findViewById<View>(R.id.floatButton) as Button
        btnSimple.setOnClickListener {
            NewbieGuide.with(this@HiBannerDemoActivity)
                .setLabel("guide1") //
//                 .setShowCounts(3)//控制次数
                .alwaysShow(true) //总是显示，调试时可以打开
                .addGuidePage(
                    GuidePage.newInstance()
                        .addHighLight(btnSimple)
//                        .addHighLight(RectF(0.0F, 800.0F, 200F, 1200F))
                        .setLayoutRes(R.layout.view_guide_simple)
                )
                .show()
        }

        floatBtn.setOnClickListener{
            val floatView = SimpleView(this)
            FloatUtil.showFloatView(floatView, null)
        }



    }

    private fun initView(hiIndicator: HiIndicator<*>?, autoPlay: Boolean) {
        this.hiIndicator = hiIndicator
        val mHiBanner = findViewById<HiBanner>(R.id.banner)
        val moList: MutableList<HiBannerMo> = ArrayList()
        for (i in 0..5) {
            val mo = BannerMo()
            mo.url = urls[i % urls.size]
            moList.add(mo)
        }
        mHiBanner!!.setHiIndicator(hiIndicator)
        mHiBanner.setAutoPlay(autoPlay)
        mHiBanner.setIntervalTime(2000)
        //自定义布局
        mHiBanner.setBannerData(R.layout.banner_item_layout, moList)
        mHiBanner.setBindAdapter { viewHolder, mo, position ->
            val imageView: ImageView = viewHolder.findViewById(R.id.iv_image)
            Glide.with(this@HiBannerDemoActivity).load(mo.url).into(imageView)
//            val titleView: TextView = viewHolder.findViewById(R.id.tv_title)
//            titleView.text = mo.url
            Log.d("----position:", position.toString() + "url:" + mo.url)
        }

    }
}