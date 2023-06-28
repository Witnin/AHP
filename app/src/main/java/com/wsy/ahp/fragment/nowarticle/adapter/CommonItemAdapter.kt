package com.wsy.ahp.fragment.nowarticle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.model.CommonItemModel


class CommonItemAdapter(mCommonItemList: MutableList<CommonItemModel>):RecyclerView.Adapter<CommonItemAdapter.CommonItemHolder>() {

    private val mCommonItemList:MutableList<CommonItemModel>? = mCommonItemList

    class CommonItemHolder(it: View):RecyclerView.ViewHolder(it){
        val icon: ImageView = it.findViewById(R.id.find_icon)
        val title: TextView = it.findViewById(R.id.find_title)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonItemAdapter.CommonItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common_item_view,parent,false)
        val viewHolder = CommonItemHolder(view)
        viewHolder.title.setOnClickListener{
            val position = viewHolder.adapterPosition
            val commonItem = mCommonItemList!![position]
            Toast.makeText(parent.context, "you clicked image ${commonItem.title}",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.icon.setOnClickListener{
            val position = viewHolder.adapterPosition
            val commonItem = mCommonItemList!![position]
            Toast.makeText(parent.context, "you clicked image ${commonItem.title}",
                Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CommonItemAdapter.CommonItemHolder, position: Int) {
        if (mCommonItemList!=null){
            val mCommonItemModel:CommonItemModel = mCommonItemList!![position]
            holder.title.text = mCommonItemModel.title
            Glide.with(holder.itemView.context).load(mCommonItemModel.url).into(holder.icon)
        }

    }

    override fun getItemCount(): Int {
       return mCommonItemList!!.size
    }
}