package com.wsy.ahp.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.MainActivity
import com.wsy.ahp.R
import com.wsy.ahp.adapter.LaunchImproveAdapter
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.ui.component.HiBaseActivity
import com.wsy.common.utils.StatusBar

@Route(path = ArouterUrl.HOME_ANSWER)
class LaunchActivity: HiBaseActivity() {
    private val lanuchImageArray = intArrayOf(
        R.drawable.guide_bg1,
        R.drawable.guide_bg2, R.drawable.guide_bg3, R.drawable.guide_bg4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val statusBar =  StatusBar(this@LaunchActivity);
        //设置颜色为半透明
        statusBar.setColor(R.color.translucent);
//        //设置颜色为透明
//        statusBar.setColor(R.color.transparent);
//        //隐藏状态栏
//        statusBar.hide();


        setContentView(R.layout.activity_launch_improve)

        val vp_launch: ViewPager = findViewById<ViewPager>(R.id.vp_launch)
        val adapter = LaunchImproveAdapter(supportFragmentManager, lanuchImageArray)
        vp_launch.adapter = adapter
    }
}