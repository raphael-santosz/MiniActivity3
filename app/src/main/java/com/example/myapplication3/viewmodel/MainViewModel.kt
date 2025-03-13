package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    // Dados para cada país
    private val data = mapOf(
        "pt" to Triple(2.4E12, 215000000, 0.20),   // Brasil: PIB, População, BRL/USD
        "en" to Triple(23.3E12, 331000000, 1.0),   // EUA: PIB, População, USD/USD
        "es" to Triple(1.4E12, 47000000, 1.08),    // Espanha: PIB, População, EUR/USD
        "de" to Triple(4.2E12, 83000000, 1.08)     // Alemanha: PIB, População, EUR/USD
    )

    // Identifica o idioma atual
    var currentLanguage = Locale.getDefault().language.take(2)
        private set

    // Função para atualizar o idioma quando o sistema muda
    fun updateLanguage() {
        currentLanguage = Locale.getDefault().language.take(2)
    }

    fun getGDP(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%.2f trilhões", values.first / 1E12)
    }

    fun getPopulation(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%,d", values.second.toLong())
    }

    fun getCurrencyValue(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%.2f", values.third)
    }

    fun getAdjustedPurchasingPower(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        val (pib, populacao, valorMoeda) = values

        val poderDeCompra = (pib / populacao) * valorMoeda
        return String.format(Locale.getDefault(), "%.2f", poderDeCompra)
    }
}
