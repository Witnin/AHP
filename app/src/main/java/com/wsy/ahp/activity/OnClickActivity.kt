package com.wsy.ahp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.coroutine.CoroutineScene
import com.wsy.ahp.coroutine.CoroutineScene3
import com.wsy.ahp.kotlin.find
import com.wsy.ahp.kotlin.onClick
import com.wsy.ahp.route.RouteFlag
import kotlinx.android.synthetic.main.activity_on_click.*
import kotlinx.coroutines.launch

@Route(path = "/auth/detail", extras = RouteFlag.FLAG_AUTHENTICATION)
class OnClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_click)
//        val textView = find<TextView>(R.id.onClick)
        R.id.onClick.onClick(  this) {
           onClick.text = " Kotlin扩展"
        }

        coroutline.setOnClickListener{
            CoroutineScene.startScene1()
        }
        coroutline1.setOnClickListener{
            CoroutineScene.startScene2()
        }

        coroutline2.setOnClickListener{
            //协程
            lifecycleScope.launch{
               val content =  CoroutineScene3.parseAssetsFile(assets,"config.json")
                Log.e("coroutine file download",content)
            }
            Log.e("coroutine file download","after click")
        }



//        lifecycleScope.async{
//
//        }
//
//        lifecycleScope.launchWhenCreated{
//            //是指当我们的宿主的生命周期 至少为oncreate的时候 才会启动
//            whenCreated {
//
//            }
//
//            whenResumed {
//
//            }
//
//            whenStarted {
//
//            }
//        }

//        lifecycleScope.launchWhenStarted {  }
//
//        GlobalScope.launch {  }

    }
}