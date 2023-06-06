package com.wsy.ahp.activity.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_login.view
import kotlinx.android.synthetic.main.activity_test_material.drawerLayout
import kotlinx.android.synthetic.main.activity_test_material.fab
import kotlinx.android.synthetic.main.activity_test_material.toolbar

@Route(path = ArouterUrl.MATERIAL_TEST)
class TestMaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_material)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        fab.setOnClickListener {view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> Toast.makeText(this, "You clicked Backup",
                Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)

        }
        return true
    }
}