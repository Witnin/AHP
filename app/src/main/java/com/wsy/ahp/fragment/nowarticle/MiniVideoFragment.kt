package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.MainTabAdapter
import com.wsy.ahp.fragment.nowarticle.viewModel.VideoViewModel
import com.wsy.common.ui.component.HiBaseFragment
import kotlinx.android.synthetic.main.fragment_mini_video.view.stl_tab_title
import kotlinx.android.synthetic.main.fragment_mini_video.view.vp_min


class MiniVideoFragment : HiBaseFragment() {

     var videoViewModel:VideoViewModel = VideoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ){
        // Inflate the layout for this fragment
        super.onViewCreated(view, savedInstanceState)

//        for (i:Int in videoViewModel.titleList.indices){
//            videoViewModel.fragmentList?.add(MinVideoListFragment())
//
//        }
        videoViewModel.typeList()
        videoViewModel.fragmentList?.add(MinVideoListFragment())
        videoViewModel.fragmentList?.add(MinVideoListFragment2())
        videoViewModel.fragmentList?.add(MinVideoListFragment())
        videoViewModel.fragmentList?.add(MinVideoListFragment2())
        videoViewModel.fragmentList?.add(MinVideoListFragment())
        videoViewModel.fragmentList?.add(MinVideoListFragment2())


        if (videoViewModel.minVideoAdapter == null){
            videoViewModel.minVideoAdapter = MainTabAdapter(childFragmentManager,videoViewModel.fragmentList,videoViewModel.titles)
        }else{
            videoViewModel.minVideoAdapter!!.notifyDataSetChanged()
        }
        view.vp_min.offscreenPageLimit = videoViewModel.fragmentList!!.size
        view.vp_min.adapter=videoViewModel.minVideoAdapter
        view.stl_tab_title.setViewPager(view.vp_min)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mini_video
    }




}