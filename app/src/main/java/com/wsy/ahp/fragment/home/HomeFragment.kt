package com.wsy.ahp.fragment.home

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.ahp.http.api.HomeApi
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.Type
import com.wsy.ahp.model.entity.TypeService
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.ui.tab.top.HiTabTopInfo
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment : HiBaseFragment() {
    private var topTabSelectIndex: Int = 0
    private val DEFAULT_SELECT_INDEX: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutView.findViewById<View>(R.id.home_answer).setOnClickListener {
            showToasts("点击")
            navigation("/home/answer")
        }

        typeList()



    }

    private fun typeList(){
        val homeApi = RetrofitServiceCreator.create(HomeApi::class.java)
        homeApi.type(1,10).enqueue(object : Callback<TypeService> {
            override fun onResponse(call: Call<TypeService>, response: Response<TypeService>) {
                if(response.isSuccessful){
                    if (response.body()!!.code == 200){
                        Log.d("home","${response.body()}")
                        val data = response.body()!!.result
                        val list = data.records.toList()
                        Log.d("home_type","$list")
                        upDateType(list)
                    }else{
                        navigation(ArouterUrl.loginUrl)
                    }
                }else{
                    navigation(ArouterUrl.loginUrl)
                }

            }

            override fun onFailure(call: Call<TypeService>, t: Throwable) {
                navigation(ArouterUrl.HOME_ANSWER)
            }

        })
    }

    private fun upDateType(list:List<Type>){
        //需要小心处理  ---viewmodel+livedata
        if (!isAlive) return

        val topTabs = mutableListOf<HiTabTopInfo<Int>>()
        list.forEachIndexed { index, tabCategory ->
            val defaultColor = ContextCompat.getColor(requireContext()!!, R.color.color_333)
            val selectColor = ContextCompat.getColor(requireContext()!!, R.color.color_dd2)
            val tabTopInfo = HiTabTopInfo<Int>(tabCategory.typeName, defaultColor, selectColor)

            topTabs.add(tabTopInfo)
        }

        val viewPager = view_pager
        val topTabLayout = tab_top_layout
        topTabLayout.inflateInfo(topTabs as List<HiTabTopInfo<*>>)
        topTabLayout.defaultSelected(topTabs[DEFAULT_SELECT_INDEX])
        topTabLayout.addTabSelectedChangeListener { index, prevInfo, nextInfo ->
            //点击之后选中的那个下标
            if (viewPager.currentItem != index) {
                viewPager.setCurrentItem(index, false)
            }
        }
        viewPager.adapter = HomePagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            list
        )
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                //这个方法被触发有两种可能，第一种切换顶部tab  第二种 手动滑动翻页
                //如果是 手动滑动翻页人

                if (position != topTabSelectIndex) {
                    //去通知topTabLayout进行切换
                    topTabLayout.defaultSelected(topTabs[position])
                    topTabSelectIndex = position
                }
            }
        })
    }


    inner class HomePagerAdapter(fm: FragmentManager, behavior: Int, val tabs: List<Type>) :
        FragmentPagerAdapter(fm, behavior) {
        val fragments = SparseArray<Fragment>(tabs.size)
        override fun getItem(position: Int): Fragment {
            var fragment = fragments.get(position, null)
            if (fragment == null) {
                fragment = HomeTabFragment.newInstance(tabs[position].articleType.toString())
                fragments.put(position, fragment)
            }
            return fragment
        }

        override fun getCount(): Int {
            return tabs.size
        }

    }

    fun showToasts(message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }


}