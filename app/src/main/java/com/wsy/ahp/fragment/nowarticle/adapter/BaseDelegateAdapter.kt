package com.wsy.ahp.fragment.nowarticle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

open class BaseDelegateAdapter(protected val context: Context, private val layoutHelper:
LayoutHelper, private val layoutId: Int, private val count: Int, protected val mViewType:
                               Int) : DelegateAdapter.Adapter<BaseViewHolder>() {
    /* 创建ViewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(v)
    }
    /* 绑定ViewHolder */
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }
    /* 多少个Item */
    override fun getItemCount(): Int {
        return count
    }
    /* LayoutHelper */
    override fun onCreateLayoutHelper(): LayoutHelper {
        return layoutHelper
    }
}