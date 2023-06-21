package com.wsy.common.utils

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.cardview.widget.CardView


object  WorksSizeCheckUtil {
    //发布作品时候填写尺寸的监听器
    var mChangeListener: IEditTextChangeListener? = null


    fun setChangeListener(changeListener: IEditTextChangeListener?) {
        if (changeListener != null) {
            mChangeListener = changeListener
        }
    }


    /**
     * 检测输入框是否都输入了内容
     * 从而改变按钮的是否可点击
     * 以及输入框后面的X的可见性，X点击删除输入框的内容
     */
    class textChangeListener(private val button: CardView) {
        private lateinit var editTexts: Array<EditText>
        fun addAllEditText(vararg editTexts: EditText): textChangeListener {
            this.editTexts = editTexts as Array<EditText>
            initEditListener()
            return this
        }

        private fun initEditListener() {
            Log.i("TAG", "调用了遍历editext的方法")
            for (editText in editTexts) {
                editText.addTextChangedListener(textChange())
            }
        }

        /**
         * edit输入的变化来改变按钮的是否点击
         */
        private inner class textChange : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (checkAllEdit()) {
                    Log.i("TAG", "所有edittext有值了")
                    mChangeListener!!.textChange(true)
                    button.isEnabled = true
                } else {
                    button.isEnabled = false
                    Log.i("TAG", "有edittext没值了")
                    mChangeListener!!.textChange(false)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        }

        /**
         * 检查所有的edit是否输入了数据
         *
         * @return
         */
        private fun checkAllEdit(): Boolean {
            for (editText in editTexts) {
                return if (!TextUtils.isEmpty(editText.text.toString() + "")) {
                    continue
                } else {
                    false
                }
            }
            return true
        }
    }
}