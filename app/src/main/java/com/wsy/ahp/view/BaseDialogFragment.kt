package com.wsy.ahp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {
    private var rootViews: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.setCancelable(true)
        if(rootViews == null){
            rootViews = inflater.inflate(getLayout(),container,false)
            val views:View? = rootViews
            initView(views!!)
            initDate()
        }
        return rootViews

    }

    protected abstract fun getLayout(): Int

    protected abstract fun initView(views: View)

    protected abstract fun initDate()


}