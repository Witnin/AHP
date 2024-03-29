package com.wsy.ahp.fragment.nowarticle.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.model.entity.Recommend

import kotlinx.android.synthetic.main.item_playlist.view.video_background
import kotlinx.android.synthetic.main.item_playlist.view.video_like
import kotlinx.android.synthetic.main.item_playlist.view.video_play_times
import kotlinx.android.synthetic.main.item_playlist.view.video_tag_ll
import kotlinx.android.synthetic.main.item_playlist.view.video_title



class VideoListAdapter(private val playlists: List<Recommend>): RecyclerView.Adapter<VideoListAdapter.VideoListHolder>()
     {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoListHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_playlist, parent,
            false)
        return VideoListHolder(v)
    }
    // 3
    override fun onBindViewHolder(holder: VideoListHolder, position: Int) {
        val playitem = playlists[position]
        holder.bindPlayItem(playitem)
    }


    // 4
    override fun getItemCount() = playlists.size
    // 5
    class VideoListHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var playItem: Recommend? = null
        // 6
        @SuppressLint("StringFormatInvalid")
        fun bindPlayItem(item: Recommend) {
            playItem = item
            view.video_title.text = item.recTitle
            view.video_like.text = "${item.articleCheckClick.likeNum}"
            if (item.recNum!! > 100000) {
                view.video_play_times.text = view.resources.getString(R.string.wan, item.recNum/ 10000)
            } else {
                view.video_play_times.text = "${item.articleCheckClick.likeNum}"
            }
//            if (item.highQuality) {
//                view.highquality_iv.visibility = View.VISIBLE
//            } else {
//                view.highquality_iv.visibility = View.INVISIBLE
//            }
//
            view.video_tag_ll.text = item.recTag!!.replace(","," ")

            Glide.with(view.context).load(item.recPic!!.replace("//gysschat","https://gysschat")).into(view.video_background)
        }


    }



}