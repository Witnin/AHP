package com.wsy.ahp.view


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.wsy.ahp.R
import com.wsy.common.utils.ButtonColorChangeUtil
import com.wsy.common.utils.IEditTextChangeListener
import com.wsy.wsy_library.util.getGlobalListener
import kotlinx.android.synthetic.main.dialog_edit.view.edit_cancel
import kotlinx.android.synthetic.main.dialog_edit.view.edit_content
import kotlinx.android.synthetic.main.dialog_edit.view.edit_finish
import kotlinx.android.synthetic.main.dialog_edit.view.edit_title
import kotlinx.android.synthetic.main.dialog_edit.view.ll_editDialog
import kotlinx.android.synthetic.main.dialog_edit.view.max_number


class EditDialog( title:String): BaseDialogFragment(),TextWatcher {

    private var editTextDialogListener:EditTextDialogListener?=null

    private var editFinish: Button? = null
    private var maxNum: TextView? = null
    private var num:Int = 0
    private var title:String = title


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.Dialog)
    }
    override fun getLayout(): Int = R.layout.dialog_edit


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        num = 30 - s.toString().length
        maxNum!!.text ="$num"
    }


    override fun initView(view: View) {
        editFinish = view.edit_finish
        maxNum = view.max_number
        view.edit_title.text = title
        val lp:WindowManager.LayoutParams = dialog?.window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog?.window!!.setGravity(Gravity.BOTTOM)

        val rootView:View = dialog!!.window!!.decorView
        rootView.viewTreeObserver.addOnGlobalLayoutListener(getGlobalListener(rootView,view.ll_editDialog))


        //1.创建工具类对象 把要改变颜色的btn先传过去
        val textChangeListener = ButtonColorChangeUtil.textChangeListener(view.edit_finish)

        //2.把所有要监听的edittext都添加进去
        textChangeListener.addAllEditText(view.edit_content)


        //3.接口回调 在这里拿到boolean变量 根据isHasContent的值决定 btn 应该设置什么颜色
        ButtonColorChangeUtil.setChangeListener(object : IEditTextChangeListener {
            override fun textChange(isHasContent: Boolean) {
                if (isHasContent) {
                    view.edit_finish.setBackgroundResource(
                        R.drawable.bg_button_blue
                    )



                } else {
                    view.edit_finish.setBackgroundResource(
                        R.drawable.bg_button
                    )
                }
            }
        })

        view.edit_cancel.setOnClickListener {
            dismiss()
        }

        view.edit_finish.setOnClickListener {
            editTextDialogListener!!.onDialog(view.edit_content.text.toString())
            dismiss()
        }



        view.edit_content.addTextChangedListener(this)

    }

    override fun initDate() {

    }

    fun setEditTextDialogListener(editTextDialogListener: EditTextDialogListener){
        this.editTextDialogListener = editTextDialogListener
    }




}