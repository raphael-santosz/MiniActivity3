package com.example.myapplication3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication3.theme.MyApplication3Theme
import com.example.myapplication3.ui.MainScreen
import com.example.myapplication3.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    // Inicializa a ViewModel
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication3Theme {
                MainScreen(mainViewModel)
            }
        }
    }
}
