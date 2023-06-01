package com.wsy.ahp.activity.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.activity.storage.database.MyDatabaseHelper
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_database.createDatabase

@Route(path = ArouterUrl.STORAGE_DATABASE)
class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 3)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
    }
}