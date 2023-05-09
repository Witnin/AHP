package com.wsy.ahp.activity

import android.graphics.Color
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.adapter.LaunchImproveAdapter
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.ui.component.HiBaseActivity
import com.wsy.wsy_library.util.HiStatusBar.setStatusBar

@Route(path = ArouterUrl.HOME_ANSWER)
class LaunchActivity: HiBaseActivity() {
    private val lanuchImageArray = intArrayOf(
        R.drawable.guide_bg1,
        R.drawable.guide_bg2, R.drawable.guide_bg3, R.drawable.guide_bg4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(this, true, Color.TRANSPARENT, false)
        setContentView(R.layout.activity_launch_improve)
        val vp_launch: ViewPager = findViewById<ViewPager>(R.id.vp_launch)
        val adapter = LaunchImproveAdapter(supportFragmentManager, lanuchImageArray)
        vp_launch.adapter = adapter
    }
}