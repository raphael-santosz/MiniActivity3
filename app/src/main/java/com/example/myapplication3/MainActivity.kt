package com.example.myapplication3

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication3.ui.MainScreen
import com.example.myapplication3.viewmodel.LocaleChangeReceiver
import com.example.myapplication3.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var localeChangeReceiver: LocaleChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()

        localeChangeReceiver = LocaleChangeReceiver {
            viewModel.updateLanguage()  // ✅ Corrigido!
        }

        // Registrar o BroadcastReceiver para capturar mudanças de idioma
        val intentFilter = IntentFilter(Intent.ACTION_LOCALE_CHANGED)
        registerReceiver(localeChangeReceiver, intentFilter)

        setContent {
            MainScreen(viewModel = viewModel, context = this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(localeChangeReceiver)
    }
}
