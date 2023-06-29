package com.wsy.ahp.activity.nowarticle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.view.DialogFragmentListener
import com.wsy.ahp.view.PhotoDialog
import com.wsy.ahp.view.UserInfoItemView
import kotlinx.android.synthetic.main.activity_user_info.change_avatar

@Route(path = ArouterUrl.USERINFO_DES)
class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        change_avatar.setUserOnClickListener(object:UserInfoItemView.UserOnClickListener{
            override fun userOnClick(view: View) {
                val mPhoto = PhotoDialog()
                mPhoto.show(supportFragmentManager,"photoDialog")
                mPhoto.setDialogFragmentListener(object :DialogFragmentListener{
                    override fun onDialog(type: Int) {
                       if(type==1){

                       }else if (type==2){

                       }else{

                       }
                    }

                })
            }

        })


    }
}