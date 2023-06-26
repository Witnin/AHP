package com.wsy.ahp.activity.nowarticle.ui

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.wsy.ahp.R
import com.wsy.ahp.http.api.LoginApi
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.LoginBody
import com.wsy.ahp.model.entity.LoginService
import com.wsy.common.utils.IEditTextChangeListener
import com.wsy.common.utils.SPUtil
import com.wsy.common.utils.WorksSizeCheckUtil.setChangeListener
import com.wsy.common.utils.WorksSizeCheckUtil.textChangeListener
import kotlinx.android.synthetic.main.activity_login.input_item_password
import kotlinx.android.synthetic.main.activity_login.input_item_username
import kotlinx.android.synthetic.main.activity_register.agreement
import kotlinx.android.synthetic.main.activity_register.imageBack
import kotlinx.android.synthetic.main.activity_register.input
import kotlinx.android.synthetic.main.activity_register.input_password
import kotlinx.android.synthetic.main.activity_register.nextStep
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Route(path = ArouterUrl.NOW_ARTICLE_REGISTER)
class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        input.width = 600

        if(SPUtil.getBoolean("agreement") && SPUtil.getString("username")!=null && SPUtil.getString("password")!=null){
            input.setText(SPUtil.getString("username"))
            input_password.setText(SPUtil.getString("password"))
            agreement.isChecked = true
            nextStep.setCardBackgroundColor(ContextCompat.getColor(this@RegisterActivity,R.color.color_298))
        }

        val username = input.text
        val password = input_password.text



        imageBack.setOnClickListener{
            finish()
        }
        nextStep.setOnClickListener {
            if(TextUtils.isEmpty(username)){

                Toast.makeText(this,"请输入账号", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(password)){

                Toast.makeText(this,"请输入密码", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
//            else if(!agreement.isChecked){
//
//                Toast.makeText(this,"同意用户协议和隐私政策后才可以登录", Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }
            else{

                goLogin()
                finish()
            }
        }


        //1.创建工具类对象 把要改变颜色的btn先传过去
        val textChangeListener = textChangeListener(nextStep)

        //2.把所有要监听的edittext都添加进去
        textChangeListener.addAllEditText(input, input_password)


        //3.接口回调 在这里拿到boolean变量 根据isHasContent的值决定 btn 应该设置什么颜色
        setChangeListener(object : IEditTextChangeListener {
            override fun textChange(isHasContent: Boolean) {
                if (isHasContent) {
                        nextStep.setCardBackgroundColor(ContextCompat.getColor(this@RegisterActivity,R.color.color_298))


                } else {
                    nextStep.setCardBackgroundColor(ContextCompat.getColor(this@RegisterActivity,R.color.color_999))
                }
            }
        })






    }

    private fun goLogin() {
        val username = input.text
        val password = input_password.text

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
            return

        val loginApi = RetrofitServiceCreator.create(LoginApi::class.java)

        val loginBody = LoginBody(username = username.toString(), password.toString())

        val requestBody = RequestBody.create(
            MediaType.parse("application/json"), Gson().toJson(loginBody) )

        loginApi.sLogin(requestBody).enqueue(object: Callback<LoginService> {
            override fun onResponse(call: Call<LoginService>, response: Response<LoginService>) {
                if (response!!.isSuccessful && response.body()!!.code==200){
                    if(agreement.isChecked){
                        SPUtil.putBoolean("agreement",true)
                        SPUtil.putString("password",password.toString())
                    }else{
                        SPUtil.putBoolean("agreement",false)
                    }
                    val data = response.body()
                    val result = data!!.result
                    Log.i("registerActivity",data!!.message)
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
                    Log.i("registerActivity",data!!.message)
                    finish()
                }else{
                    val data = response.body()
                    Log.i("registerActivity",data!!.message)
                    showToasts(getString(R.string.login_failed)+data!!.message)
                }
            }

            override fun onFailure(call: Call<LoginService>, t: Throwable) {
                Log.i("registerActivity", t.toString())
                showToasts(getString(R.string.login_failed)+t)
            }

        })


    }

    fun showToasts(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }







}