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
import androidx.core.content.ContextCompat
import com.wsy.ahp.R
import com.wsy.common.ui.component.HiBaseFragment
import com.wsy.common.utils.SPUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import com.wsy.common.ui.view.loadCircle

class UserFragment:HiBaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upDateUI()
    }

    private fun upDateUI() {
        Log.d("user-username","${SPUtil.getString("username")}")
        Log.d("user-introduce","${SPUtil.getString("introduce")}")
        Log.d("user-avatar","${SPUtil.getString("avatar")}")
        user_name.text = SPUtil.getString("username")?:""
        login_desc.text = SPUtil.getString("introduce")?:""
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
}