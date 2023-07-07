package com.wsy.ahp.fragment.nowarticle

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.GridLayoutManager
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.VideoListAdapter
import com.wsy.ahp.fragment.nowarticle.api.VideoApiService

import com.wsy.ahp.model.entity.Recommend


import kotlinx.android.synthetic.main.fragment_video_list.video_list_rv
import kotlinx.android.synthetic.main.fragment_video_list.video_no_data
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class VideoListFragment : Fragment() {
    private val myJob = Job()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i("请求错误信息", "${throwable.message}")
    }
    private val myScope = CoroutineScope(myJob + Dispatchers.Main + exceptionHandler)


    private lateinit var grideLayoutManager: GridLayoutManager

    private var videoItemList: ArrayList<Recommend> = ArrayList()

    private lateinit var adapter: VideoListAdapter

    private lateinit var catName: String

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
//        arguments?.getString(QueryKey)?.let {
//            video_text.text = it
//        }
        arguments?.getString(QueryKey)?.let {
            catName = it
        }

        requestData()


        val screenHeight = Resources.getSystem().displayMetrics.heightPixels //屏幕高度
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels//屏幕宽度

        if(screenHeight>screenWidth){
            grideLayoutManager = GridLayoutManager(requireActivity(), 3)
        }else{
            grideLayoutManager = GridLayoutManager(requireActivity(), 6)
        }
        video_list_rv.layoutManager = grideLayoutManager

        adapter = VideoListAdapter(videoItemList)
// 3
        video_list_rv.adapter = adapter


    }

    override fun onDestroy() {
        super.onDestroy()
        myScope.cancel()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun requestData(){
        myScope.launch {
            when(catName){
                "水质"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,0)
                    videoItemList.addAll(response.result.records)
                }
                "土壤"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,1)
                    videoItemList.addAll(response.result.records)
                }
                "气体"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,2)
                    videoItemList.addAll(response.result.records)

                }
                "食品"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,3)
                    videoItemList.addAll(response.result.records)
                }
                "噪音"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,4)
                    videoItemList.addAll(response.result.records)
                }
                "其他"->{
                    val response = VideoApiService.create().getRecommendList(1, 20,5)
                    videoItemList.addAll(response.result.records)
                }

            }
            if(videoItemList.isEmpty()){
                video_no_data.visibility = View.VISIBLE
            }
            adapter.notifyDataSetChanged()
        }
    }


}