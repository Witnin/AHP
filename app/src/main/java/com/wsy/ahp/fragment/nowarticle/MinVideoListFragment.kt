package com.wsy.ahp.fragment.nowarticle

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.MinVideoAdapter
import com.wsy.ahp.fragment.nowarticle.viewModel.MinVideoViewModel
import com.wsy.common.ui.component.HiBaseFragment
import kotlinx.android.synthetic.main.fragment_min_video_list.view.rv_video_list

class MinVideoListFragment() :HiBaseFragment(){

    private var views:View? = null

    var videoViewModel: MinVideoViewModel = MinVideoViewModel()



    override fun getLayoutId(): Int = R.layout.fragment_min_video_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views =view

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels //屏幕高度
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels//屏幕宽度

        if(screenHeight>screenWidth){
            view.rv_video_list.layoutManager=GridLayoutManager(activity,4)
        }else{
            view.rv_video_list.layoutManager=GridLayoutManager(activity,6)
        }





        view.rv_video_list.adapter = MinVideoAdapter(videoViewModel.mList)
    }
}