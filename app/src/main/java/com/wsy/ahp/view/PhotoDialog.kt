package com.wsy.ahp.view


import android.view.View
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.dialog_photo.view.*

class PhotoDialog: BaseDialogFragment() {

    private var mDialogFragmentListener:DialogFragmentListener?=null
    override fun getLayout(): Int = R.layout.dialog_photo

    override fun initView(view: View) {
        view.select_from_album.setOnClickListener{
            mDialogFragmentListener!!.onDialog(1)
            dismiss()
        }
        view.take_photo.setOnClickListener{
            mDialogFragmentListener!!.onDialog(2)
            dismiss()
        }
        view.photo_cancel.setOnClickListener{
           dismiss()
        }
    }

    override fun initDate() {

    }

    fun setDialogFragmentListener(mDialogFragmentListener: DialogFragmentListener){
        this.mDialogFragmentListener = mDialogFragmentListener
    }
}