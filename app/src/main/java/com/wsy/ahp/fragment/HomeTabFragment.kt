package org.devio.`as`.proj.main.fragment.home

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wsy.common.ui.component.HiAbsListFragment




class HomeTabFragment : HiAbsListFragment() {
    private var categoryId: String? = null
    val DEFAULT_HOT_TAB_CATEGORY_ID = "1"

    companion object {
        fun newInstance(categoryId: String): HomeTabFragment {
            val args = Bundle()
            args.putString("categoryId", categoryId)
            val fragment =
                HomeTabFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryId = arguments?.getString("categoryId", DEFAULT_HOT_TAB_CATEGORY_ID)

        super.onViewCreated(view, savedInstanceState)

        queryTabCategoryList()

        enableLoadMore { queryTabCategoryList() }
    }

    override fun onRefresh() {
        super.onRefresh()

        queryTabCategoryList()
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        val isHotTab = TextUtils.equals(categoryId, DEFAULT_HOT_TAB_CATEGORY_ID)
        return if (isHotTab) super.createLayoutManager() else GridLayoutManager(context, 2)
    }

    private fun queryTabCategoryList() {
        showToasts("homeTab")
    }

    fun showToasts(message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }


}