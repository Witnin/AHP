package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.common.ui.component.HiBaseFragment

class MyFragment: HiBaseFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

}