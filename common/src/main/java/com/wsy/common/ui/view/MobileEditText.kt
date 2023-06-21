package com.wsy.common.ui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.wsy.common.R

/**
 * author : within
 * time   : 2023/06/21
 * desc   : 自定义手机号码输入框
 */
class MobileEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int = com.google.android.material.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) , TextWatcher {

    //设置默认的删除图标
    private var draw: Drawable?=null
    private var drawShow: Drawable?=null
    //初始化
    init {
        draw= ContextCompat.getDrawable(context, R.mipmap.icon_delete)

        //设置最小宽度和最小高度
        val   minimumWidth: Int=draw!!.minimumWidth
        val   minimumHeight: Int=draw!!.minimumHeight
        draw!!.setBounds(0,0,minimumWidth,minimumHeight)
        isShow(false)
    }
    //控制显示隐藏
    private fun isShow(isShow:Boolean){
        drawShow=if(isShow){
            draw
        }
        else{
            null
        }
        setCompoundDrawables(compoundDrawables[0],compoundDrawables[1],drawShow,compoundDrawables[3])
    }

    //有输入内容显示 没有内容不显示
    override fun afterTextChanged(s: Editable?) {
        if(TextUtils.isEmpty(text.toString())){
            isShow(false)
        }else{
            isShow(true)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event!!.action==MotionEvent.ACTION_DOWN){
            val isDelete:Boolean = event.x>(width-totalPaddingRight)&&
                    event.x<(width -paddingRight)&&event.y>0 &&event.y < height
            if (isDelete){
                setText("")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
    }
}