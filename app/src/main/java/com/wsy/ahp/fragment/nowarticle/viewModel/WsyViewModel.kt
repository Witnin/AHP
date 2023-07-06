package com.wsy.ahp.fragment.nowarticle.viewModel

import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter

open class WsyViewModel: ViewModel() {
     fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }

}