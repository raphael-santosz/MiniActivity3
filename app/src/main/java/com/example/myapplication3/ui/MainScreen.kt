package com.example.myapplication3.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication3.R
import com.example.myapplication3.viewmodel.MainViewModel
import android.util.Log
import java.util.*

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    var gdp by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    var currencyValue by remember { mutableStateOf("") }
    var poderDeCompra by remember { mutableStateOf("") }

    // Captura o idioma atual para acionar o LaunchedEffect
    val currentLanguage = Locale.getDefault().language.take(2)

    // LaunchedEffect para capturar mudanças no idioma e atualizar os dados
    LaunchedEffect(currentLanguage) {
        Log.d("DEBUG", "Idioma atual = $currentLanguage")
        gdp = viewModel.getGDP()
        population = viewModel.getPopulation()
        currencyValue = viewModel.getCurrencyValue()
        poderDeCompra = viewModel.getAdjustedPurchasingPower()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.greeting))

            Text(text = stringResource(id = R.string.current_language))

            Text(text = stringResource(id = R.string.gdp, gdp))
            Text(text = stringResource(id = R.string.population, population))
            Text(text = stringResource(id = R.string.currency_value, currencyValue))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                poderDeCompra = viewModel.getAdjustedPurchasingPower()
            }) {
                Text(text = stringResource(id = R.string.calculate_purchasing_power))
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (poderDeCompra.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.purchasing_power) + ": $poderDeCompra USD",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
