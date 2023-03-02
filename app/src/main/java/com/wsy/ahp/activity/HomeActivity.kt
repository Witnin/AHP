package com.wsy.ahp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wsy.ahp.R
import com.wsy.ahp.route.RouteFlag

@Route(path = "/home/detail", extras = RouteFlag.FLAG_LOGIN)
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //隐藏导航栏
        val actionbar = supportActionBar
        actionbar?.hide()

//        val navView: BottomNavigationView = findViewById(R.id.button_case)
//        val navController= findNavController(R.id.button_case)
//
//        val appBarConfiguration= AppBarConfiguration(
//            set0f(
//            R.id.navigation_home,
//            R.id.navigation_dashboard,
//            R.id.navigation_notifications))
//
//                    setupActionBarWithNavController(navController,appBarConfiguration)
//                    navView.setupWithNavController(navController)
    }

//    override fun onResume() {
//        val nav_view
//        super.onResume()
//        Log.e( "onresume", nav_view.width.toString() + nav_view.height)
//        nav_view.post {
//            Log.e( "onresume:post", nav_view.width.toString() + "--" + nav_view.height)
//        } }
//    nav_view.viewTreeObserver.addOnGlobalLayoutListener {
//        nav_view.viewTreeObserver.remove0nGlobalLayoutListener { this }
//
//        Log.e("onresume:iewtreeobserver", nav_view.width.toString()+"-"+ nav_view.height)
//    }
}