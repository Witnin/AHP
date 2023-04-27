package com.wsy.ahp.fragment.home

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.ahp.http.api.HomeApi
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.Recommend
import com.wsy.ahp.model.entity.RecommendService
import com.wsy.ahp.model.entity.TypeService
import com.wsy.common.ui.component.HiAbsListFragment
import com.wsy.ui.item.HiDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeTabFragment : HiAbsListFragment() {
    private var recommendId: String? = null
    private val DEFAULT_HOT_TAB_CATEGORY_ID = "0"

    companion object {
        fun newInstance(recommendId: String): HomeTabFragment {
            val args = Bundle()
            args.putString("recommendId", recommendId)
            val fragment =
                HomeTabFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recommendId = arguments?.getString("recommendId", DEFAULT_HOT_TAB_CATEGORY_ID)

        super.onViewCreated(view, savedInstanceState)

        queryTabCategoryList()

        enableLoadMore { queryTabCategoryList() }
    }

    override fun onRefresh() {
        super.onRefresh()
        queryTabCategoryList()
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        val isHotTab = TextUtils.equals(recommendId, DEFAULT_HOT_TAB_CATEGORY_ID)
        return if (isHotTab) super.createLayoutManager() else GridLayoutManager(context, 2)
    }

    private fun queryTabCategoryList() {
        val homeApi = RetrofitServiceCreator.create(HomeApi::class.java)
        homeApi.recommend(pageIndex,10,recommendId!!.toInt()).enqueue(object : Callback<RecommendService> {
            override fun onResponse(call: Call<RecommendService>, response: Response<RecommendService>) {
                if(response.isSuccessful){
                    if (response.body()!!.code == 200){
                        Log.d("home_tab","${response.body()}")
                        val data = response.body()!!.result
                        val list = data.records.toList()
                        Log.d("home_tab_list","$list")
                        updateRecommendUI(list)
                    }else{
                        showToasts(getString(R.string.login_failed)+response.message())
                        navigation(ArouterUrl.loginUrl)
                       
                    }
                }else{
                    navigation(ArouterUrl.loginUrl)
                }

            }

            override fun onFailure(call: Call<RecommendService>, t: Throwable) {
                showToasts(getString(R.string.login_failed)+t)
            }

        })
    }

    private fun updateRecommendUI(list: List<Recommend>) {
        if (!isAlive) return
        val dataItems = mutableListOf<HiDataItem<*, *>>()
        list.forEachIndexed{index, recommend ->
            dataItems.add(
                RecommendItem(
                    recommend,
                    TextUtils.equals(recommendId,DEFAULT_HOT_TAB_CATEGORY_ID)
                )
            )
        }
        finishRefresh(dataItems)
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }

    fun showToasts(message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }


}