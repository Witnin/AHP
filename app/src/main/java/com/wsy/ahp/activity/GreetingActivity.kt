package com.wsy.ahp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class GreetingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column {
                    GreetingScreen("user1")
                    GreetingScreen("user2")
                    Text(text = "你发，wsy")
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(
    userId: String,
    viewModel: GreetingViewModel = viewModel(
        factory = GreetingViewModelFactory(userId)
    )
) {
    val messageUser by viewModel.message.observeAsState("")
    Text(messageUser)
}

class GreetingViewModel(private val userId: String) : ViewModel() {
    private val _message = MutableLiveData("Hi $userId")
    val message: LiveData<String> = _message
}

class GreetingViewModelFactory(private val userId: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GreetingViewModel(userId) as T
    }
}