package com.wsy.ahp.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.wsy.ahp.R
import com.wsy.ahp.http.api.LoginApi
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.model.entity.Banner
import com.wsy.ahp.model.entity.CommonService
import com.wsy.ahp.route.HiRoute
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.common.ui.view.loadCircle
import com.wsy.common.ui.view.loadCorner
import com.wsy.common.utils.SPUtil
import com.wsy.ui.banner.core.HiBannerAdapter
import com.wsy.ui.banner.core.HiBannerMo
import com.wsy.wsy_library.util.HiDisplayUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import me.reezy.cosmo.update.UpdateManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment:HiBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upDateUI()
        bannerList()
    }



    private fun bannerList(){
        val loginApi = RetrofitServiceCreator.create(LoginApi::class.java)

        loginApi.banner(1,10).enqueue(object: Callback<CommonService>
             {
            override fun onResponse(call: Call<CommonService>, response: Response<CommonService>) {
                if (response.body()!!.code == 200){
                    val data = response.body()!!.result
                    val bannerList = data.records.toList()
                    Log.d("user-bannerList","$bannerList")
                    updateBanner(bannerList)
                }else{
                    showToasts(getString(R.string.get_failed)+response.message())
                }
            }

            override fun onFailure(call: Call<CommonService>, t: Throwable) {
                showToasts(getString(R.string.get_failed)+t)

            }

        })
    }

    private fun updateBanner(bannerNoticeList: List<Banner>?) {
        if (bannerNoticeList == null || bannerNoticeList.isEmpty()) return
        var models = mutableListOf<HiBannerMo>()
        bannerNoticeList.forEach {
            var hiBannerMo = object : HiBannerMo() {}
            hiBannerMo.url = it.bannerUrl
            models.add(hiBannerMo)
        }
        hi_banner.setOnBannerClickListener { viewHolder, bannerMo, position ->
            HiRoute.startActivity4Browser(bannerNoticeList[position].bannerRemarks)
        }
        hi_banner.setBannerData(R.layout.layout_profile_banner_item, models)
        hi_banner.setBindAdapter { viewHolder: HiBannerAdapter.HiBannerViewHolder?, mo: HiBannerMo?, position: Int ->
            if (viewHolder == null || mo == null) return@setBindAdapter
            val imageView = viewHolder.findViewById<ImageView>(R.id.banner_item_imageview)
            imageView.loadCorner(mo.url, HiDisplayUtil.dp2px(10f, resources))
        }
        hi_banner.visibility = View.VISIBLE


    }

    fun showToasts(message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    private fun upDateUI() {
        Log.d("user-username","${SPUtil.getString("username")}")
        Log.d("user-introduce","${SPUtil.getString("introduce")}")
        Log.d("user-avatar","${SPUtil.getString("avatar")}")
        user_name.text = SPUtil.getString("username")?:""
        login_desc.text = SPUtil.getString("introduce")?:""
        if(SPUtil.getString("avatar")==null){
            user_avatar.setImageResource(R.drawable.ic_avatar_default)
            user_avatar.setOnClickListener{
                navigation(ArouterUrl.loginUrl)
            }
        }
        user_avatar.loadCircle(SPUtil.getString("avatar")?:"")

        tab_item_collection.text =
            spannableTabItem(
                10,
                getString(R.string.profile_tab_item_collection)
            )
        tab_item_history.text =
            spannableTabItem(20, getString(R.string.profile_tab_item_history))
        tab_item_learn.text =
            spannableTabItem(30, getString(R.string.profile_tab_item_learn))

        notify_count.text = "10"
        notify_count.visibility = View.VISIBLE
    }

    /**
     * 数字文本分割
     */
    private fun spannableTabItem(topText: Int, bottomText: String): CharSequence? {
        val spanStr = topText.toString()
        var ssb = SpannableStringBuilder()
        var ssTop = SpannableString(spanStr)

        val spanFlag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        ssTop.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.color_000)),
            0,
            ssTop.length,
            spanFlag
        )
        ssTop.setSpan(AbsoluteSizeSpan(18, true), 0, ssTop.length, spanFlag)
        ssTop.setSpan(StyleSpan(Typeface.BOLD), 0, ssTop.length, spanFlag)

        ssb.append(ssTop)
        ssb.append(bottomText)

        return ssb
    }

    private fun navigation(s: String) {
        ARouter.getInstance().build(s).navigation()
    }
}