package com.wsy.ahp.activity.thread

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.os.Handler
import android.os.Looper

import android.os.Message
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_relative_layout_brother.textView
import kotlinx.android.synthetic.main.activity_thread_uiupdate.changeTextBtn
import kotlin.concurrent.thread

@Route(path = ArouterUrl.THREAD_UI_UPDATE)
class ThreadUIUpdateActivity : AppCompatActivity() {

    val updateText = 1
    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            when (msg.what) {
                updateText -> textView.text = "Nice to meet you"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_uiupdate)
        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg) // 将Message对象发送出去
            }
        }

    }
}