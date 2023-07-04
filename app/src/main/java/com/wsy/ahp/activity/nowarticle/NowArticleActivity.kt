package com.wsy.ahp.activity.nowarticle

import android.annotation.SuppressLint
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.wsy.ahp.R
import com.wsy.ahp.activity.nowarticle.adapter.HomeAdapter
import com.wsy.ahp.activity.nowarticle.model.TitleModel
import com.wsy.ahp.fragment.nowarticle.ArticleHomeFragment
import com.wsy.ahp.fragment.nowarticle.MiniVideoFragment
import com.wsy.ahp.fragment.nowarticle.MyFragment
import com.wsy.ahp.fragment.nowarticle.VideoFragment
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.ui.component.BaseActivity
import com.wsy.common.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_now_article.ctl_home
import kotlinx.android.synthetic.main.activity_now_article.vp_home

@Route(path = ArouterUrl.NOW_ARTICLE)
class NowArticleActivity : BaseActivity() {

    private val titleTabs = ArrayList<CustomTabEntity>()

    private val fragments = ArrayList<Fragment>()
    override fun getLayoutId(): Int = R.layout.activity_now_article

    override fun initData() {
        val titles = resources.getStringArray(R.array.title)
        val selectIds: TypedArray = resources.obtainTypedArray(R.array.selected)
        val unSelectIds: TypedArray = resources.obtainTypedArray(R.array.unselected)
        fragments.add(ArticleHomeFragment())
        fragments.add(VideoFragment())
        fragments.add(MiniVideoFragment())
        fragments.add(MyFragment())

        //遍历title 拼接tabs
        for (i: Int in titles.indices) {
            titleTabs.add(
                TitleModel(
                    titles[i],
                    unSelectIds.getResourceId(i, 0),
                    selectIds.getResourceId(i, 0)
                )
            )
        }
        //viewPage添加适配器
        vp_home.adapter = HomeAdapter(supportFragmentManager, fragments)
        vp_home.offscreenPageLimit = fragments.size
        vp_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
            }

        })
        ctl_home.setTabData(titleTabs)
        ctl_home.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                vp_home.setCurrentItem(
                    position,
                    false
                )//smoothScroll控制ViewPager动画，false关闭后ViewPager不会闪烁
                when(position){
                    0,1,2 -> setLightStatusBar()
                    3 ->setTransStatusBar()
                }
            }

            override fun onTabReselect(position: Int) {
                vp_home.setCurrentItem(
                    position,
                    false
                )//smoothScroll控制ViewPager动画，false关闭后ViewPager不会闪烁
            }


        })

        ctl_home.showDot(1)
        ctl_home.showMsg(0, 55);
        ctl_home.setMsgMargin(0, -5f, 2f);

        ctl_home.showMsg(2, 100);
        ctl_home.setMsgMargin(2, -5f, 1f);
    }

    override fun init() {

    }
}