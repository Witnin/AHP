package com.wsy.ahp.view

import android.view.View
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.dialog_gender.view.*
import kotlinx.android.synthetic.main.dialog_photo.view.select_from_album
import kotlinx.android.synthetic.main.dialog_photo.view.take_photo

class GenderDialog: BaseDialogFragment() {
    private var mDialogFragmentListener:DialogFragmentListener?=null
    override fun getLayout(): Int = R.layout.dialog_gender

    override fun initView(view: View) {
        view.man.setOnClickListener{
            mDialogFragmentListener!!.onDialog(1)
            dismiss()
        }
        view.woman.setOnClickListener{
            mDialogFragmentListener!!.onDialog(2)
            dismiss()
        }
        view.cancel.setOnClickListener{
            dismiss()
        }
    }

    override fun initDate() {

    }

    fun setDialogFragmentListener(mDialogFragmentListener: DialogFragmentListener){
        this.mDialogFragmentListener = mDialogFragmentListener
    }
}