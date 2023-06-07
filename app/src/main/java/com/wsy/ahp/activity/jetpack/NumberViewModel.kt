package com.wsy.ahp.activity.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class NumberViewModel(countReserved: Int): ViewModel() {
    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    private val userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }





    init {
        _counter.value = countReserved
    }




    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }
    fun clear() {
        _counter.value = 0
    }

}