package com.wsy.ahp.activity.nowarticle.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.ui.component.HiBaseActivity
import com.wsy.wsy_library.util.HiStatusBar
import kotlinx.android.synthetic.main.activity_settings.logout
import kotlinx.android.synthetic.main.activity_settings.settings_night
import kotlinx.android.synthetic.main.activity_settings.tv_image
import kotlinx.android.synthetic.main.activity_settings.yi_ji
import kotlinx.android.synthetic.main.settings_item_view.view.btn_off
import kotlinx.android.synthetic.main.settings_item_view.view.left_title

@Route(path = ArouterUrl.NOW_ARTICLE_SETTING)
class SettingsActivity : HiBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settings_night.btn_off.isChecked=true
        HiStatusBar.setStatusBar(this,true, Color.WHITE,false)
//        settings_night.left_title.text = "测试数据"

//        yi_ji.setBgItem(R.color.color_eed)
        tv_image.setOnClickListener {
            finish()
        }

        logout.setOnClickListener{
            navigation(ArouterUrl.NOW_ARTICLE_REGISTER)
        }
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }
}