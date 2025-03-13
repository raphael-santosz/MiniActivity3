package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    // Dados para cada país
    private val data = mapOf(
        "pt" to Triple(2.4E12, 215000000, 0.20),   // Brasil
        "en" to Triple(23.3E12, 331000000, 1.0),   // EUA
        "es" to Triple(1.4E12, 47000000, 1.08),    // Espanha
        "de" to Triple(4.2E12, 83000000, 1.08)     // Alemanha
    )

    // Identifica o idioma atual
    private val currentLanguage = Locale.getDefault().language.take(2)

    // Retorna o valor do PIB formatado para trilhões
    fun getGDP(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%.2f trilhões", values.first / 1E12)
    }

    // Retorna o valor da população
    fun getPopulation(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%,d", values.second.toLong())
    }

    // Retorna o valor da moeda em relação ao dólar
    fun getCurrencyValue(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        return String.format(Locale.getDefault(), "%.2f", values.third)
    }

    // Calcula o poder de compra ajustado
    fun getAdjustedPurchasingPower(): String {
        val values = data[currentLanguage] ?: data["en"]!!
        val (pib, populacao, valorMoeda) = values

        val poderDeCompra = (pib / populacao) * valorMoeda
        return String.format(Locale.getDefault(), "%.2f", poderDeCompra)
    }
}
