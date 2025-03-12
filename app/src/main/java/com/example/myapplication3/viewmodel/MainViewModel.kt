package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Locale

class MainViewModel : ViewModel() {

    // Idioma atual do sistema
    var currentLanguage by mutableStateOf(Locale.getDefault().language)
        private set

    // Saudação inicial
    var greetingMessage by mutableStateOf("")

    fun setGreetingMessage(message: String) {
        greetingMessage = message
    }
}
