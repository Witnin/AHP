package com.wsy.ahp.activity.nowarticle.model

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * author : within
 * time   : 2023/6/20
 * desc   : title数据模型
 */
data class TitleModel(val title: String, val select: Int, val unSelect: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int =unSelect
    override fun getTabSelectedIcon(): Int=select
    override fun getTabTitle(): String =title
}