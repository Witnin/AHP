package com.wsy.ahp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.kotlin.find
import com.wsy.ahp.kotlin.onClick
import com.wsy.ahp.route.RouteFlag
import kotlinx.android.synthetic.main.activity_on_click.*

@Route(path = "/auth/detail", extras = RouteFlag.FLAG_AUTHENTICATION)
class OnClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_click)
//        val textView = find<TextView>(R.id.onClick)
        R.id.onClick.onClick(  this) {
           onClick.text = " Kotlin扩展"
        }
    }
}