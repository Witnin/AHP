package com.wsy.ahp.fragment.nowarticle.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.model.entity.Recommend
import com.wsy.wsy_library.util.HiDisplayUtil

import kotlinx.android.synthetic.main.item_playlist.view.video_background
import kotlinx.android.synthetic.main.item_playlist.view.video_like
import kotlinx.android.synthetic.main.item_playlist.view.video_play_times
import kotlinx.android.synthetic.main.item_playlist.view.video_tag_ll
import kotlinx.android.synthetic.main.item_playlist.view.video_title



class VideoListAdapter(private val playlists: List<Recommend>): RecyclerView.Adapter<VideoListAdapter.PlaylistItemHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistItemHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_playlist, parent,
            false)
        return PlaylistItemHolder(v)
    }
    // 3
    override fun onBindViewHolder(holder: PlaylistItemHolder, position: Int) {
        val playitem = playlists[position]
        holder.bindPlayItem(playitem)
    }


    // 4
    override fun getItemCount() = playlists.size
    // 5
    class PlaylistItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
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