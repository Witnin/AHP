package com.wsy.ahp.activity.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_calculator.clearBtn
import kotlinx.android.synthetic.main.activity_calculator.infoText
import kotlinx.android.synthetic.main.activity_calculator.plusOneBtn

@Route(path = ArouterUrl.VIEWMODEL_CAL)
class CalculatorActivity : AppCompatActivity() {

    lateinit var viewModel: NumberViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this,NumberViewModelFactory(countReserved)).get(NumberViewModel::class.java)
        plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }

        refreshCounter()

    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }


}