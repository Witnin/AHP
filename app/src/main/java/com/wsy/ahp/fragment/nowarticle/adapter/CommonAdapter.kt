package com.wsy.ahp.fragment.nowarticle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.model.CommonModel

class CommonAdapter(mCommonList:MutableList<CommonModel>):RecyclerView.Adapter<CommonAdapter.CommonHolder>() {
    private val mCommonList:MutableList<CommonModel>? = mCommonList

    inner class CommonHolder(it: View):RecyclerView.ViewHolder(it){
        val mTitle: TextView = it.findViewById(R.id.title)
        val seeMore: TextView = it.findViewById(R.id.see_more)
        val mCommonList:RecyclerView = it.findViewById(R.id.rv_common_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common_view,parent,false)
        val viewHolder = CommonHolder(view)
        viewHolder.mTitle
        viewHolder.seeMore.setOnClickListener{
            val position = viewHolder.adapterPosition
            val more = mCommonList!![position]
            Toast.makeText(parent.context, "you clicked image ${more.title}",
                Toast.LENGTH_SHORT).show()
        }
        return viewHolder

    }



    override fun getItemCount(): Int =mCommonList!!.size

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        if(mCommonList !=null){
            val mCommonModel:CommonModel = mCommonList!![position]
            holder.mTitle.text=mCommonModel.title
            if(mCommonModel.list!=null){
                holder.mCommonList.layoutManager = GridLayoutManager(holder.itemView.context,6)
                holder.mCommonList.adapter=CommonItemAdapter(mCommonModel.list)
            }
        }
    }
}