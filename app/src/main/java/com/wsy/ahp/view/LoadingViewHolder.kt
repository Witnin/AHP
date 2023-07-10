package com.wsy.ahp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.footer_loading.view.loading_iv
import kotlinx.android.synthetic.main.footer_loading.view.loading_tv

class LoadingViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        companion object {
            // 1
            fun instance(parent: ViewGroup): LoadingViewHolder {
                val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.footer_loading,
                    parent, false)
                return LoadingViewHolder(v)
            }
        }
        // 2
        fun bindNetWorkStatus(loadingStatus: LoadingStatus?) {
// 3
            when(loadingStatus) {
                LoadingStatus.Failed -> {
                    itemView.loading_tv.text = "点击重试"
                    itemView.loading_iv.visibility = View.GONE
                    itemView.isClickable = true
                }
                LoadingStatus.Completed -> {
                    itemView.loading_tv.text = "加载完毕"
                    itemView.loading_iv.visibility = View.GONE
                    itemView.isClickable = false
                }
                LoadingStatus.Loading -> {
                    itemView.loading_tv.text = "加载中..."
                    itemView.loading_iv.visibility = View.VISIBLE
                    itemView.isClickable = false
                }

                else -> {}
            }
        }

    }