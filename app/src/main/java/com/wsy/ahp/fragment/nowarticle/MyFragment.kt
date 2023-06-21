package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.common.ui.view.CountDownListener
import com.wsy.common.utils.AuthCodeTimer
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment: HiBaseFragment(),CountDownListener {

    private var mAuthCodeTimer: AuthCodeTimer?=null
    private  var s:String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuthCodeTimer = AuthCodeTimer(1*60*1000,1000,this)
        mAuthCodeTimer!!.start()

        layoutView.findViewById<View>(R.id.toutiao_register).setOnClickListener {
            navigation("/article/register")
        }




    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }

    override fun countDown(time: Long) {

        if(time>60*1000){
            s=("${time/1000/60}分${time/1000 % 60}秒")
        }else{
            s = ("${time/1000}秒")
        }

        countDown.text = s
        countDown.isEnabled = false
    }

    override fun isOverTime(isOverTime: Boolean) {
        countDown.text = "获取"
        countDown.isEnabled = true
    }

}