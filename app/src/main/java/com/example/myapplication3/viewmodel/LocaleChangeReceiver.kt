package com.example.myapplication3.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class LocaleChangeReceiver(private val onLocaleChanged: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_LOCALE_CHANGED) {
            Log.d("LocaleChangeReceiver", "Idioma do sistema alterado")
            onLocaleChanged()
        }
    }
}
