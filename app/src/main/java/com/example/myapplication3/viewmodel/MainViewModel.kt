package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import java.util.Locale

class MainViewModel : ViewModel() {

    // Estado para armazenar o idioma atual
    private val _currentLanguage = mutableStateOf(getSystemLanguage())
    val currentLanguage: State<String> = _currentLanguage

    // Função que obtém o idioma atual do sistema
    private fun getSystemLanguage(): String {
        return Locale.getDefault().displayLanguage // Exibe o nome do idioma (ex.: "Español", "Português")
    }
}
