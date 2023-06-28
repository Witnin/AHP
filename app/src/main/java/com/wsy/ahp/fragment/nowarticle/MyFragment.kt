package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.CommonAdapter
import com.wsy.ahp.fragment.nowarticle.model.CommonItemModel
import com.wsy.ahp.fragment.nowarticle.model.CommonModel
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.common.ui.view.CountDownListener
import com.wsy.common.utils.AuthCodeTimer
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_my.view.rv_common_list

class MyFragment: HiBaseFragment(),CountDownListener {

    private var mAuthCodeTimer: AuthCodeTimer?=null
    private  var s:String? = null

    private val mCommonList:MutableList<CommonItemModel> = ArrayList()
    private val mCommonList1:MutableList<CommonItemModel> = ArrayList()
    private val mList:MutableList<CommonModel>? = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuthCodeTimer = AuthCodeTimer(1*60*1000,1000,this)
        mAuthCodeTimer!!.start()

        layoutView.findViewById<View>(R.id.toutiao_login).setOnClickListener {
            navigation("/article/register")
        }

        layoutView.findViewById<View>(R.id.toutiao_setting).setOnClickListener {
            navigation("/article/setting")
        }

        val item = CommonItemModel("我的关注","")
        mCommonList.add(item)
        val item1 =CommonItemModel("测试功能","")
        mCommonList1.add(item1)


        val commonModel = CommonModel("常用",mCommonList)
        val commonModel1 = CommonModel("发现",mCommonList1)
        mList!!.add(commonModel)
        mList.add(commonModel1)
        view.rv_common_list.layoutManager = LinearLayoutManager(activity)
        view.rv_common_list.adapter = CommonAdapter(mList)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }

    override fun countDown(time: Long) {

        s = if(time>60*1000){
            ("${time/1000/60}分${time/1000 % 60}秒")
        }else{
            ("${time/1000}秒")
        }

        countDown.text = s
        countDown.isEnabled = false
    }

    override fun isOverTime(isOverTime: Boolean) {
        countDown.text = "获取"
        countDown.isEnabled = true
    }

}