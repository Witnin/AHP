package com.wsy.ahp.fragment.nowarticle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.model.CommonModel

class CommonAdapter(mCommonList:MutableList<CommonModel>):RecyclerView.Adapter<CommonAdapter.CommonHolder>() {
    private val mCommonList:MutableList<CommonModel>? = mCommonList

    class CommonHolder(it: View):RecyclerView.ViewHolder(it){
        val mTitle: TextView = it.findViewById(R.id.title)
        val mCommonList:RecyclerView = it.findViewById(R.id.rv_common_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonHolder =
        CommonHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_common_view,parent,false))


    override fun getItemCount(): Int =mCommonList!!.size

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        if(mCommonList !=null){
            val mCommonModel:CommonModel = mCommonList!![position]
            holder.mTitle.text=mCommonModel.title

        }
    }
}