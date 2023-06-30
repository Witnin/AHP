package com.wsy.ahp.activity.nowarticle.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.view.DialogFragmentListener
import com.wsy.ahp.view.GenderDialog
import com.wsy.ahp.view.GlideEngine
import com.wsy.ahp.view.PhotoDialog
import com.wsy.ahp.view.UserInfoItemView
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.fragment_my.avatar_Image
import kotlinx.android.synthetic.main.item_user_info.view.user_info_item_avatar


@Route(path = ArouterUrl.USERINFO_DES)
class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        tv_image.setOnClickListener {
            finish()
        }

        change_avatar.setUserOnClickListener(object : UserInfoItemView.UserOnClickListener {
            override fun userOnClick(view: View) {
                selectPhoto()
//                val mPhoto = PhotoDialog()
//                mPhoto.show(supportFragmentManager, "photoDialog")
//                mPhoto.setDialogFragmentListener(object : DialogFragmentListener {
//                    override fun onDialog(type: Int) {
//                        Log.i("UserInfoActivity","onDialog")
//                        when (type) {
//                            1 -> {
//                                selectPhoto()
//                            }
//                            2 -> {
//                                Log.i("UserInfoActivity","2")
//                            }
//                            else -> {
//
//                            }
//                        }
//                    }
//                })
            }
        })

        user_info_sex.setUserOnClickListener(object : UserInfoItemView.UserOnClickListener {
            override fun userOnClick(view: View) {
                val mPhoto = GenderDialog()
                mPhoto.show(supportFragmentManager, "sexDialog")
                mPhoto.setDialogFragmentListener(object : DialogFragmentListener {
                    override fun onDialog(type: Int) {
                        Log.i("UserInfoActivity","onDialog")
                        when (type) {
                            1 -> {
                                user_info_sex.setTextRight("男")
                            }
                            2 -> {
                                user_info_sex.setTextRight("女")
                            }
                            else -> {

                            }
                        }
                    }
                })
            }
        })



        user_info_real_name.setTextRight("小张")
    }

    private fun selectPhoto() {
        PictureSelector.create(this@UserInfoActivity)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setSelectionMode(1)//单选
            .forResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>) {
                    Log.i("UserInfoActivity","1")
                    for (media in result) {
                        change_avatar.setImageUrl(media!!.path)
                    }
                }
                override fun onCancel() {

                }
            })
    }

}