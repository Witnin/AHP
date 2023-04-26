package com.wsy.ahp.fragment

import android.os.Bundle
import com.wsy.ahp.R
import com.wsy.common.ui.component.HiBaseFragment


class HomeFragment : HiBaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}