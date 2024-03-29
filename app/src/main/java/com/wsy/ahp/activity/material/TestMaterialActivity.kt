package com.wsy.ahp.activity.material

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.kotlin.showSnackbar
import kotlinx.android.synthetic.main.activity_login.view
import kotlinx.android.synthetic.main.activity_test_material.drawerLayout
import kotlinx.android.synthetic.main.activity_test_material.fab
import kotlinx.android.synthetic.main.activity_test_material.material_recyclerView
import kotlinx.android.synthetic.main.activity_test_material.swipeRefresh
import kotlinx.android.synthetic.main.activity_test_material.toolbar
import kotlin.concurrent.thread

@Route(path = ArouterUrl.MATERIAL_TEST)
class TestMaterialActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana",
        R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon",
        R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape",
        R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry",
        R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango",
        R.drawable.mango))
    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_material)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        fab.setOnClickListener {view ->
//            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
//                .setAction("Undo") {
//                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
//                }
//                .show()
            view.showSnackbar("This is Snackbar", "Action") {
                Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
            }
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        material_recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        material_recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    @SuppressLint("RtlHardcoded")
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