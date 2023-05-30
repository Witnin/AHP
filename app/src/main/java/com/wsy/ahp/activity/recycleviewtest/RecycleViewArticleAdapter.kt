package com.wsy.ahp.activity.recycleviewtest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.wsy.ahp.R
import com.wsy.ahp.activity.article.Article

class RecycleViewArticleAdapter(val articleList:List<Article>):RecyclerView.Adapter<RecycleViewArticleAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage: ImageView = view.findViewById(R.id.articleImage)
        val articleName: TextView = view.findViewById(R.id.articleName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_horizontal_article_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = articleList[position]
        holder.articleImage.setImageResource(fruit.imageId)
        holder.articleName.text = fruit.name
    }
}