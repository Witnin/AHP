package com.wsy.ahp.activity.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NumberViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NumberViewModel(countReserved) as T
    }

}