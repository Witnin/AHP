package com.wsy.ahp.fragment.nowarticle

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.adapter.CommonAdapter
import com.wsy.ahp.fragment.nowarticle.model.CommonItemModel
import com.wsy.ahp.fragment.nowarticle.model.CommonModel
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.common.ui.view.CountDownListener
import com.wsy.common.utils.AuthCodeTimer
import com.wsy.common.utils.SPUtil
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_my.view.rv_common_list
import kotlinx.android.synthetic.main.item_view.view.image

class MyFragment: HiBaseFragment(),CountDownListener {

    private var mAuthCodeTimer: AuthCodeTimer?=null
    private  var s:String? = null

    private val mCommonList:MutableList<CommonItemModel> = ArrayList()
    private val mCommonList1:MutableList<CommonItemModel> = ArrayList()
    private val mList:MutableList<CommonModel>? = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = layoutView.findViewById<ImageView>(R.id.avatar_Image)
        if(SPUtil.getString("X-Access-Token") !=null){
            my_rl.visibility= View.GONE
            login_success.visibility = View.VISIBLE
            Glide.with(this).load(SPUtil.getString("avatar")).into(imageView)
            username.text="姓名  ${SPUtil.getString("realname")}"
        }else{
            my_rl.visibility= View.VISIBLE
            login_success.visibility = View.GONE
        }
        mAuthCodeTimer = AuthCodeTimer(1*60*1000,1000,this)
        mAuthCodeTimer!!.start()

        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(Color.parseColor("#FFFFFFFF"), Color.parseColor("#f4f4f4")))
        cl_login.background = gradientDrawable

        layoutView.findViewById<View>(R.id.toutiao_login).setOnClickListener {
            navigation("/article/register")
        }

        layoutView.findViewById<View>(R.id.toutiao_des).setOnClickListener {
            navigation("/userinfo/des")
        }

        layoutView.findViewById<View>(R.id.certification).setOnClickListener {
            PopupWindow().apply {
                //入口参数配置
                contentView = layoutInflater.inflate(R.layout.popwindow_layout,null)
                width = 190
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                isFocusable = true

                //设置按钮
                val btnPopupWindow = contentView.findViewById<View>(R.id.popBtn)
                btnPopupWindow.setOnClickListener {
                    Toast.makeText(context,"退出弹窗！！",Toast.LENGTH_SHORT).show()
                    dismiss()
                }

                //显示在按钮的下方
                showAsDropDown(it)
                //显示在底部
//                showAtLocation(it,Gravity.BOTTOM,0,0)


            }

        }

        val item = CommonItemModel("锁定信息","https://kcqzypt.oss-cn-beijing.aliyuncs.com/upload/password.png")
        mCommonList.add(item)
        val item2 = CommonItemModel("底部导航","https://kcqzypt.oss-cn-beijing.aliyuncs.com/upload/password.png", ArouterUrl.NOW_ARTICLE_NAV)
        mCommonList.add(item2)
        val item3 = CommonItemModel("吸顶测试",R.mipmap.icon_case.toString(),ArouterUrl.stickyHeader)
        mCommonList.add(item3)
        val item4 = CommonItemModel("3D翻转","https://kcqzypt.oss-cn-beijing.aliyuncs.com/upload/password.png",ArouterUrl.threeDimen)
        mCommonList.add(item4)
        val item5 = CommonItemModel("悬浮窗","https://kcqzypt.oss-cn-beijing.aliyuncs.com/upload/password.png",ArouterUrl.flb)
        mCommonList.add(item5)
        val item6 = CommonItemModel("我的关注6",R.mipmap.tab_home_selected.toString())
        mCommonList.add(item6)
        val item7 = CommonItemModel("我的关注7",R.mipmap.tab_my_selected.toString())
        mCommonList.add(item7)
        val item8 = CommonItemModel("我的关注8",R.mipmap.tab_home_selected.toString())
        mCommonList.add(item8)
        val item9 = CommonItemModel("我的关注9",R.mipmap.icon_delete.toString())
        mCommonList.add(item9)
        val item10 = CommonItemModel("我的关注10",R.mipmap.tab_home_selected.toString())
        mCommonList.add(item10)
        val item11 = CommonItemModel("我的关注11",R.mipmap.right.toString())
        mCommonList.add(item11)
        val item12 = CommonItemModel("我的关注12",R.mipmap.tab_home_selected.toString())
        mCommonList.add(item12)
        val item13 = CommonItemModel("我的关注13","https://himg.bdimg.com/sys/portrait/item/wise.1.1319eb91.0L-dJDwdzE2JYC9XPR-Kkg.jpg")
        mCommonList.add(item13)
        val item14 = CommonItemModel("我的关注14",R.mipmap.tab_home_selected.toString())
        mCommonList.add(item14)
        val item15 = CommonItemModel("我的关注15",R.mipmap.username.toString())
        mCommonList.add(item15)
        val item16 = CommonItemModel("设置中心","https://kcqzypt.oss-cn-beijing.aliyuncs.com/upload/setting.png","/article/setting")
        mCommonList.add(item16)

        val item1 =CommonItemModel("测试功能",R.mipmap.tab_home_selected.toString())
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