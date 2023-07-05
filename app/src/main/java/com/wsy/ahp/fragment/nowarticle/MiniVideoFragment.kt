package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.MainTabAdapter
import com.wsy.common.ui.component.HiBaseFragment
import kotlinx.android.synthetic.main.fragment_mini_video.stl_tab_title
import kotlinx.android.synthetic.main.fragment_mini_video.view.stl_tab_title
import kotlinx.android.synthetic.main.fragment_mini_video.view.vp_min
import kotlinx.android.synthetic.main.fragment_mini_video.vp_min


class MiniVideoFragment : HiBaseFragment() {

    private var fragmentList:MutableList<Fragment>? = mutableListOf()
    private var titles:Array<String> = arrayOf()
    private var minVideoAdapter:MainTabAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ){
        // Inflate the layout for this fragment
        super.onViewCreated(view, savedInstanceState)
        titles=resources.getStringArray(R.array.video)
        for (i:Int in titles.indices){
            fragmentList?.add(MinVideoListFragment())

        }
        if (minVideoAdapter == null){
            minVideoAdapter = MainTabAdapter(childFragmentManager,fragmentList,titles)
        }else{
            minVideoAdapter!!.notifyDataSetChanged()
        }
        view.vp_min.offscreenPageLimit = fragmentList!!.size
        view.vp_min.adapter=minVideoAdapter
        view.stl_tab_title.setViewPager(view.vp_min)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mini_video
    }


}