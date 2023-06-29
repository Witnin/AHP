package com.wsy.ahp.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.item_user_info.view.rl_user_info_item
import kotlinx.android.synthetic.main.item_user_info.view.user_info_item_avatar
import kotlinx.android.synthetic.main.item_user_info.view.user_info_item_des
import kotlinx.android.synthetic.main.item_user_info.view.user_info_item_left_title
import kotlinx.android.synthetic.main.settings_item_view.view.rl_item


class UserInfoItemView @JvmOverloads constructor(
    context:Context,attrs:AttributeSet?=null,defStyleAttr:Int = 0)
    : FrameLayout(context, attrs,defStyleAttr) {
    private var leftTitle:String? = ""
    private var isShowImage:Boolean = false
    private var view: View? = null

    private val userTypeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.UserItem)

    private var mUserOnClickListener:UserOnClickListener?=null

    init {
        leftTitle = userTypeArray.getString(R.styleable.UserItem_user_leftTitle)
        isShowImage = userTypeArray.getBoolean(R.styleable.UserItem_user_isShowImage,false)
        userTypeArray.recycle()
        initView()
    }

    private fun initView() {
        view= LayoutInflater.from(context).inflate(R.layout.item_user_info,this)
        view!!.user_info_item_left_title.text = leftTitle
        view!!.rl_user_info_item.setOnClickListener{
            mUserOnClickListener!!.userOnClick(it)
        }
        isShowImage(view!!)
    }

    private fun isShowImage(view:View){
        if (isShowImage){
            view.user_info_item_avatar.visibility = View.VISIBLE
        }else{
            view.user_info_item_avatar.visibility = View.GONE
        }
    }

    fun setTextRight(content:String){
        view!!.user_info_item_des.text = content
    }

    fun setTextRightColor(colorIds:Int){
        view!!.user_info_item_des.setTextColor(ContextCompat.getColor(context,colorIds))
    }

    fun setImageUrl(url:String){
        Glide.with(context).load(url).into(user_info_item_avatar)
    }

    fun setUserOnClickListener(mUserOnClickListener: UserOnClickListener){
        this.mUserOnClickListener = mUserOnClickListener
    }

    interface UserOnClickListener{
        fun userOnClick(view:View)
    }
}