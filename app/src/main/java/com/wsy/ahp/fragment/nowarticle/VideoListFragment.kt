package com.wsy.ahp.fragment.nowarticle

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*

import androidx.recyclerview.widget.GridLayoutManager

import com.wsy.ahp.R

import com.wsy.ahp.fragment.nowarticle.adapter.VideoListItemAdapter
import com.wsy.ahp.fragment.nowarticle.datasource.VideoListDataSourceFactory

import com.wsy.ahp.fragment.nowarticle.viewModel.VideoViewModel
import kotlinx.android.synthetic.main.fragment_video_list.*


class VideoListFragment : Fragment() {

    private lateinit var grideLayoutManager: GridLayoutManager


    private lateinit var adapter: VideoListItemAdapter


    companion object {
        const val QueryKey = "query_key"
        fun getInstance(key: String): VideoListFragment {
            val fragment = VideoListFragment()
            Bundle().also {
                it.putString(QueryKey, key)
                fragment.arguments = it
            }
            return fragment
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
                    arguments?.getString(QueryKey)?.let {
                        return VideoViewModel(it) as T
                    }

                }
                throw IllegalArgumentException(" unKnown ViewModel class ")
            }
        }).get(VideoViewModel::class.java)

//        arguments?.getString(QueryKey)?.let {
//            video_text.text = it
//        }
        adapter = VideoListItemAdapter()

//        arguments?.getString(QueryKey)?.let {
//           viewModel.type = it
//        }

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels //屏幕高度
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels//屏幕宽度

        grideLayoutManager = if (screenHeight > screenWidth) {
            GridLayoutManager(requireActivity(), 3)
        } else {
            GridLayoutManager(requireActivity(), 6)
        }
        video_list_rv.layoutManager = grideLayoutManager

// 3
        video_list_rv.adapter = adapter

        viewModel.pagedListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

//        viewModel.videoList.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//            if( viewModel.videoList.value!!.isEmpty()){
//                video_no_data.visibility = View.VISIBLE
//            }
//
//        })
//
//        viewModel.videoList.value?:viewModel.requestData()


    }

//    override fun onDestroy() {
//        super.onDestroy()
//        myScope.cancel()
//    }


}