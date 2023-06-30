package com.wsy.ahp.view


import android.view.View
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.dialog_photo.view.*

class EditDialog: BaseDialogFragment() {

    private var mDialogFragmentListener:DialogFragmentListener?=null
    override fun getLayout(): Int = R.layout.dialog_edit

    override fun initView(view: View) {

    }

    override fun initDate() {

    }

    fun setDialogFragmentListener(mDialogFragmentListener: DialogFragmentListener){
        this.mDialogFragmentListener = mDialogFragmentListener
    }
}