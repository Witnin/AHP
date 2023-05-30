package com.wsy.ahp.activity.recycleviewtest.waterflow

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.wsy.ahp.R
import com.wsy.ahp.activity.article.Article

class RecycleViewWaterFlowAdapter(val articleList:List<Article>):RecyclerView.Adapter<RecycleViewWaterFlowAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage: ImageView = view.findViewById(R.id.articleImage)
        val articleName: TextView = view.findViewById(R.id.articleName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_water_flow_article_item, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.articleName.setOnClickListener {
            val position = viewHolder.adapterPosition
            val article = articleList[position]
            Toast.makeText(parent.context, "you clicked view ${article.name}",
                Toast.LENGTH_SHORT).show()
        }
        viewHolder.articleImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val article = articleList[position]
            Toast.makeText(parent.context, "you clicked image ${article.name}",
                Toast.LENGTH_SHORT).show()
        }
        return viewHolder

    }

    override fun getItemCount(): Int  = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = articleList[position]
        holder.articleImage.setImageResource(fruit.imageId)
        holder.articleName.text = fruit.name
    }
}