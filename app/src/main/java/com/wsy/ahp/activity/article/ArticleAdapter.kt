package com.wsy.ahp.activity.article

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.wsy.ahp.R

class ArticleAdapter(activity: Activity, val resourceId: Int, data: List<Article>):ArrayAdapter<Article>(activity,resourceId,data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val viewHolder: ViewHolder
       if (convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
           val articleImage: ImageView = view.findViewById(R.id.articleImage)
           val articleName: TextView = view.findViewById(R.id.articleName)
            viewHolder = ViewHolder(articleImage, articleName)
           view.tag = viewHolder
       } else {
           view = convertView
           viewHolder = view.tag as ViewHolder
       }


        val fruit = getItem(position) // 获取当前项的Fruit实例
        if (fruit != null) {
            viewHolder.articleImage.setImageResource(fruit.imageId)

            viewHolder.articleName.text = fruit.name
        }
        return view
    }

    inner class ViewHolder(val articleImage: ImageView, val articleName: TextView)
}