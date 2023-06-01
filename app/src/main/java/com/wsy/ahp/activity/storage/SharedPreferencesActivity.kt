package com.wsy.ahp.activity.storage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_shared_preferences.restoreButton
import kotlinx.android.synthetic.main.activity_shared_preferences.saveButton
@Route(path = ArouterUrl.STORAGE_SP)
class SharedPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        saveButton.setOnClickListener {
//            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
//            editor.putString("name", "Tom")
//            editor.putInt("age", 28)
//            editor.putBoolean("married", false)
//            editor.apply()
            getSharedPreferences("data",Context.MODE_PRIVATE).edit{
                putString("name", "wsy")
                putInt("age", 18)
                putBoolean("married", true)
            }
        }

        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("SharedPreferencesActivity", "name is $name")
            Log.d("SharedPreferencesActivity", "age is $age")
            Log.d("SharedPreferencesActivity", "married is $married")
        }
    }
}