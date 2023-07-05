package com.wsy.ahp.fragment.nowarticle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.model.MinVideoModel

class MinVideoAdapter(minVideoList: MutableList<MinVideoModel>):RecyclerView.Adapter<MinVideoAdapter.MinVideoHolder>() {

    private val minVideoList:MutableList<MinVideoModel> = minVideoList

    class MinVideoHolder(it: View):RecyclerView.ViewHolder(it){
        val videoBackground:ImageView = it.findViewById(R.id.video_background)
        val videoTitle:TextView = it.findViewById(R.id.video_title)
        val videoLike:TextView = it.findViewById(R.id.video_like)
        val videoPlayTimes:TextView = it.findViewById(R.id.video_play_times)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinVideoHolder
        = MinVideoHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_min_video,parent,false))

    override fun getItemCount(): Int {
        return minVideoList.size
    }

    override fun onBindViewHolder(holder: MinVideoHolder, position: Int) {
       if (minVideoList!=null){
           val minVideoModel:MinVideoModel = minVideoList[position]
           Glide.with(holder.itemView.context).load(minVideoModel.url).into(holder.videoBackground)
           holder.videoLike.text = "${minVideoModel.like}点赞"
           holder.videoTitle.text = minVideoModel.title
           holder.videoPlayTimes.text = "${minVideoModel.playTimes}次播放"
       }
    }
}