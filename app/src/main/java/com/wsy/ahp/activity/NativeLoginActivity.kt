package com.wsy.ahp.activity

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wsy.ahp.R
import com.wsy.ahp.http.api.LoginApi
import com.wsy.ahp.http.api.TestApi
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.model.entity.LoginBody
import com.wsy.ahp.model.entity.LoginService
import com.wsy.common.ui.component.HiBaseActivity
import com.wsy.common.utils.SPUtil
import com.wsy.wsy_library.util.HiStatusBar
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Route(path = ArouterUrl.loginUrl)
class NativeLoginActivity : HiBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        HiStatusBar.setStatusBar(this,true, Color.WHITE,false)
        action_back.setOnClickListener{
            onBackPressed()
        }

        action_register.setOnClickListener{
            goRegister()
        }

        action_login.setOnClickListener{
            goLogin()
        }
    }

    private fun goRegister() {

    }

    private fun getRecommend(){
        RetrofitServiceCreator.create(TestApi::class.java).recommend(1,10).enqueue(object:Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    val result = response.body()
                    showToasts(getString(R.string.login_success)+result)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showToasts(getString(R.string.login_failed)+t)
            }

        })
    }

    private fun goLogin() {
        val username = input_item_username.text
        val password = input_item_password.text

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
            return

        val loginApi = RetrofitServiceCreator.create(LoginApi::class.java)

        val loginBody = LoginBody(username = username.toString(), password.toString())

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(), Gson().toJson(loginBody) )

        loginApi.sLogin(requestBody).enqueue(object:Callback<LoginService>{
            override fun onResponse(call: Call<LoginService>, response: Response<LoginService>) {
                if (response!!.isSuccessful){
                    val data = response.body()
                    val result = data!!.result
                    val username = data.result.userInfo.username
                    val realname = data.result.userInfo.realname
                    val avatar = data.result.userInfo.avatar
                    val introduce = data.result.userInfo.introduce
                    val backgroundUrl = data.result.userInfo.backgroundUrl
                    val matureTime = data.result.userInfo.matureTime
                    val token = data.result.token
                    SPUtil.putString("X-Access-Token",token)
                    SPUtil.putString("username",username)
                    SPUtil.putString("realname",realname)
                    SPUtil.putString("avatar",avatar)
                    SPUtil.putString("introduce",introduce)
                    SPUtil.putString("backgroundUrl",backgroundUrl)
                    SPUtil.putString("matureTime",matureTime)
                    showToasts(getString(R.string.login_success)+data!!.message)
                    finish()
                }else{
                    val data = response.body()
                    showToasts(getString(R.string.login_failed)+data!!.message)
                }
            }

            override fun onFailure(call: Call<LoginService>, t: Throwable) {

                showToasts(getString(R.string.login_failed)+t)
            }

        })


    }





      fun showToasts(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }
}
