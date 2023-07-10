package com.wsy.ahp.fragment.nowarticle.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.viewModel.VideoViewModel
import com.wsy.ahp.model.entity.Recommend
import com.wsy.ahp.view.LoadingStatus
import com.wsy.ahp.view.LoadingViewHolder
import kotlinx.android.synthetic.main.item_playlist.view.video_background
import kotlinx.android.synthetic.main.item_playlist.view.video_like
import kotlinx.android.synthetic.main.item_playlist.view.video_play_times
import kotlinx.android.synthetic.main.item_playlist.view.video_tag_ll
import kotlinx.android.synthetic.main.item_playlist.view.video_title
import kotlinx.coroutines.NonDisposableHandle

class VideoListItemAdapter(private val viewModel: VideoViewModel): PagedListAdapter<Recommend, RecyclerView.ViewHolder>(DiffCallback) {




    private lateinit var _loadingStatus: LoadingStatus


    // 1
    private var hasLoadingFooter = false
    // 2
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasLoadingFooter) 1 else 0
    }
    // 3
    override fun getItemViewType(position: Int): Int {
        return if (hasLoadingFooter && position == itemCount - 1) R.layout.footer_loading
        else R.layout.item_playlist
    }

        // 2
        object DiffCallback: DiffUtil.ItemCallback<Recommend>() {
            override fun areItemsTheSame(oldItem: Recommend, newItem: Recommend): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: Recommend, newItem: Recommend): Boolean {
                return oldItem.recTitle == newItem.recTitle && oldItem.recPic ==
                        newItem.recPic
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        LoadingViewHolder.instance(parent).also {
            it.itemView.setOnClickListener {
                viewModel.retry()
            }
        }

        return when (viewType) {
            R.layout.item_playlist -> {
                VideoListItemHolder.instance(parent)
            }

            else -> {
                LoadingViewHolder.instance(parent)
            }
        }
    }



    // 3
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

//        val playitem = playlists[position]
//        holder.bindPlayItem(playitem)

//        holder.bindPlayItem(getItem(position))


        when(holder.itemViewType) {
            R.layout.footer_loading -> {
                (holder as LoadingViewHolder).bindNetWorkStatus(_loadingStatus)
            }
            else -> {
                getItem(position)?.let {
                    (holder as VideoListItemHolder).bindPlayItem(it)
                }
            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        var layoutManager:RecyclerView.LayoutManager = recyclerView.layoutManager!!
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == R.layout.footer_loading) {
                        layoutManager.spanCount // Footer时返回三个的单元格，从而占据整个一行的宽度
                    } else {
                        1 // 正常情况下返回一个单元格
                    }
                }
            }
        }
    }

    class VideoListItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var playItem: Recommend? = null

        companion object {
            // 1
            fun instance(parent: ViewGroup): VideoListItemHolder {

                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist,
                    parent, false)
                return VideoListItemHolder(v)
            }
        }

        // 6
        @SuppressLint("StringFormatInvalid")
        fun bindPlayItem(item: Recommend?) {
            playItem = item
            view.video_title.text = item!!.recTitle
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



    // 更新加载状态
    fun updateLoadingStatus(loadingStatus: LoadingStatus) {
        _loadingStatus = loadingStatus
        if (loadingStatus == LoadingStatus.InitalLoading) {
            hideLoading()
        } else {
            showLoading()
        }
    }
    private fun hideLoading() {
        if (hasLoadingFooter) {
            notifyItemRemoved(itemCount - 1)
        }
        hasLoadingFooter = false
    }

    private fun showLoading() {
        if (hasLoadingFooter) {
            notifyItemChanged(itemCount - 1)
        } else {
            hasLoadingFooter = true
            notifyItemInserted(itemCount - 1)
        }
    }

}