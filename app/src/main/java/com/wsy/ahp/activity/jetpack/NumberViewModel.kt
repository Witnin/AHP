package com.wsy.ahp.activity.jetpack

import androidx.lifecycle.ViewModel

class NumberViewModel(countReserved: Int): ViewModel() {
    var counter = countReserved
}