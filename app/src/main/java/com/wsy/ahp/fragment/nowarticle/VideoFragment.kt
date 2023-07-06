package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.VideoAdapter
import com.wsy.ahp.fragment.nowarticle.api.VideoApiService
import kotlinx.android.synthetic.main.fragment_video.video_tab_layout
import kotlinx.android.synthetic.main.fragment_video.video_vp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class VideoFragment : Fragment() {

    private val myJob = Job()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i("请求错误信息", "${throwable.message}")
    }
    private val myScope = CoroutineScope(myJob + Dispatchers.Main + exceptionHandler)

    private lateinit var videoListNamesArray: Array<String>

    private val viewPagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Toast.makeText(requireActivity(), "选择了${videoListNamesArray[position]}",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myScope.launch {
            val response = VideoApiService.create().getType(1, 10)
            println("$response")
            Log.i("视频VideoFragment", "${response.result.records}")
        }

        videoListNamesArray = resources.getStringArray(R.array.video)
// 2
        VideoAdapter(this, videoListNamesArray).also {
            video_vp.adapter = it
        }

        video_vp.registerOnPageChangeCallback(viewPagerChangeCallback)

        TabLayoutMediator(video_tab_layout, video_vp) { tab, position ->
            tab.text = videoListNamesArray[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        myScope.cancel()
    }



}