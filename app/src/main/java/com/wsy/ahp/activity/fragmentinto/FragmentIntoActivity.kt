package com.wsy.ahp.activity.fragmentinto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.fragment.article.AnotherRightFragment
import com.wsy.ahp.fragment.article.RightFragment
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.fragment_left.button

@Route(path = ArouterUrl.FRAGMENT_REPLACE)
class FragmentIntoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_into)
        button.setOnClickListener{
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null) //实现返回栈
        transaction.commit()
    }

}