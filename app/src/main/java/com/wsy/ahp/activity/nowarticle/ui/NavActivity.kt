package com.wsy.ahp.activity.nowarticle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.bottom_nav_bar.centerIcon

@Route(path = ArouterUrl.NOW_ARTICLE_NAV)
class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        centerIcon.setOnClickListener {
            Toast.makeText(applicationContext,"中间", Toast.LENGTH_SHORT).show()
        }
    }
}