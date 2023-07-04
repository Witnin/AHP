package com.wsy.ahp.activity.nowarticle.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.wsy.ahp.R
import com.wsy.ahp.http.api.CommonApi
import com.wsy.ahp.http.api.LoginApi
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.UploadService
import com.wsy.ahp.view.DialogFragmentListener
import com.wsy.ahp.view.EditDialog
import com.wsy.ahp.view.EditTextDialogListener
import com.wsy.ahp.view.GenderDialog
import com.wsy.ahp.view.GlideEngine
import com.wsy.ahp.view.UserInfoItemView
import com.wsy.common.utils.SPUtil
import com.wsy.wsy_library.util.getTime
import kotlinx.android.synthetic.main.activity_user_info.*

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.util.Calendar


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

        user_info_birthday.setUserOnClickListener(object : UserInfoItemView.UserOnClickListener {
            override fun userOnClick(view: View) {
                selectDate()
            }
        })



        user_info_sex.setUserOnClickListener(object : UserInfoItemView.UserOnClickListener {
            override fun userOnClick(view: View) {
                val mPhoto = GenderDialog()
                mPhoto.show(supportFragmentManager, "sexDialog")
                mPhoto.setDialogFragmentListener(object : DialogFragmentListener {
                    override fun onDialog(type: Int) {
                        Log.i("UserInfoActivity", "onDialog")
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

        user_info_real_name.setUserOnClickListener(object : UserInfoItemView.UserOnClickListener {
            override fun userOnClick(view: View) {
                val mPhoto = EditDialog("请输入昵称")
                mPhoto.show(supportFragmentManager, "editDialog")
                mPhoto.setEditTextDialogListener(object : EditTextDialogListener {
                    override fun onDialog(content: String) {
                        user_info_real_name.setTextRight(content)
                    }
                })
            }
        })


    }

    private fun selectPhoto() {
        PictureSelector.create(this@UserInfoActivity)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .setSelectionMode(1)//单选
            .forResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>) {
                    Log.i("UserInfoActivity", "1")
                    for (media in result) {
                        change_avatar.setImageUrl(media!!.path)
                        Log.i("UserInfoActivity", media!!.path)
                        val file =File(media!!.realPath)
                        Log.i("UserInfoActivity", file.toString())
                        uploadImage(file)
                    }
                }

                override fun onCancel() {

                }
            })
    }

    //
//    * 选择日期第三方库使用
    fun selectDate() {
        val startDate = Calendar.getInstance()
        startDate.set(1900, 1, 1)
        val endDate: Calendar = Calendar.getInstance()
        TimePickerBuilder(this@UserInfoActivity,
            OnTimeSelectListener { date, _ ->
                getTime(date)?.let { user_info_birthday.setTextRight(it) }
            })
            .setCancelText("取消")
            .setSubmitText("确认")
            .setCancelColor(ContextCompat.getColor(this@UserInfoActivity, R.color.color_298))
            .setSubmitColor(ContextCompat.getColor(this@UserInfoActivity, R.color.color_298))
            .setBgColor(Color.WHITE)
            .setDate(endDate)
            .setRangDate(startDate, endDate)
            .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
            .setTitleSize(20)
            .build().show()
    }

    fun uploadImage(file: File){
        val uploadApi = RetrofitServiceCreator.create(CommonApi::class.java)
        uploadApi.uploadImage(getParam(file)).enqueue(object:retrofit2.Callback<UploadService>{
            override fun onResponse(call: Call<UploadService>, response: Response<UploadService>) {
                if (response!!.isSuccessful){
                    val data = response.body()
                    val result = data!!.message
                    SPUtil.putString("avatar",result)
                    showToasts(getString(R.string.upload_success))
                    finish()
                }else{
                    val data = response.body()
                    showToasts(getString(R.string.upload_failed))
                    Log.i("UserInfoActivity",data!!.message)
                }
            }
            override fun onFailure(call: Call<UploadService>, t: Throwable) {
                showToasts(getString(R.string.upload_failed)+t)
                Log.i("UserInfoActivity", t.toString())
            }

        })
    }
    private fun getParam(file: File): MultipartBody.Part {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"),file)
        return MultipartBody.Part.createFormData("file",file.name,requestBody)
    }

    fun showToasts(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }


}