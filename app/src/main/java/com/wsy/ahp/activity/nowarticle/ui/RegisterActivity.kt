package com.wsy.ahp.activity.nowarticle.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.common.utils.IEditTextChangeListener
import com.wsy.common.utils.WorksSizeCheckUtil.setChangeListener
import com.wsy.common.utils.WorksSizeCheckUtil.textChangeListener
import kotlinx.android.synthetic.main.activity_register.agreement
import kotlinx.android.synthetic.main.activity_register.imageBack
import kotlinx.android.synthetic.main.activity_register.input
import kotlinx.android.synthetic.main.activity_register.input_password
import kotlinx.android.synthetic.main.activity_register.nextStep


@Route(path = ArouterUrl.NOW_ARTICLE_REGISTER)
class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
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
            else if(!agreement.isChecked){

                Toast.makeText(this,"同意用户协议和隐私政策后才可以登录", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{

                Toast.makeText(this,"操作成功", Toast.LENGTH_LONG).show()
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







}