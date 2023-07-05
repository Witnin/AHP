package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.MinVideoAdapter
import com.wsy.ahp.fragment.nowarticle.model.CommonItemModel
import com.wsy.ahp.fragment.nowarticle.model.CommonModel
import com.wsy.ahp.fragment.nowarticle.model.MinVideoModel
import com.wsy.common.ui.component.HiBaseFragment
import kotlinx.android.synthetic.main.fragment_min_video_list.view.rv_video_list

class MinVideoListFragment:HiBaseFragment(){

    private var views:View? = null

    private val mList:MutableList<MinVideoModel>? = ArrayList()
    override fun getLayoutId(): Int = R.layout.fragment_min_video_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views =view
        view.rv_video_list.layoutManager=GridLayoutManager(activity,3)

        val item7 = MinVideoModel("极光之夜","https://kcqzypt.oss-cn-beijing.aliyuncs.com/2022/08/11/0df65084705b7d60234a402b2ad88e9_1660198313678.jpg",1,2)
        mList!!.add(item7)

        view.rv_video_list.adapter = MinVideoAdapter(mList)
    }
}