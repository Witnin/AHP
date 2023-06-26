package com.wsy.ahp.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.settings_item_view.view.btn_off
import kotlinx.android.synthetic.main.settings_item_view.view.content_title
import kotlinx.android.synthetic.main.settings_item_view.view.iv_right_back
import kotlinx.android.synthetic.main.settings_item_view.view.left_title
import kotlinx.android.synthetic.main.settings_item_view.view.right_title

class SettingsItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,defStyleAttr:Int = 0
) : FrameLayout(context, attrs,defStyleAttr) {
    private var leftTitle:String? = ""
    private var contentTitle:String? = ""
    private var rightTitle:String? = ""
    private var isShowButton:Boolean = false
    private var isShowImage:Boolean = false

    private val settingsArray:TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingsItem)
    init {
        leftTitle = settingsArray.getString(R.styleable.SettingsItem_leftTitle)
        contentTitle = settingsArray.getString(R.styleable.SettingsItem_contentTitle)
        rightTitle = settingsArray.getString(R.styleable.SettingsItem_rightTitle)
        isShowButton = settingsArray.getBoolean(R.styleable.SettingsItem_isShowButton,false)
        isShowImage = settingsArray.getBoolean(R.styleable.SettingsItem_isShowImage,false)
        settingsArray.recycle()
        initView()
    }

    private fun initView() {
        val view: View = LayoutInflater.from(context).inflate(R.layout.settings_item_view,this)
        view.left_title.text = leftTitle
        view.right_title.text = rightTitle
        view.content_title.text = contentTitle
        isShowBtn(view)
        isShowImage(view)
    }

    private fun isShowBtn(view:View){
        if (isShowButton){
            view.btn_off.visibility = View.VISIBLE
        }else{
            view.btn_off.visibility = View.GONE
        }
    }

    private fun isShowImage(view:View){
        if (isShowImage){
            view.iv_right_back.visibility = View.VISIBLE
        }else{
            view.iv_right_back.visibility = View.GONE
        }
    }
}