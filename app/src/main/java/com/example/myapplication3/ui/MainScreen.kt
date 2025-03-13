package com.example.myapplication3.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication3.R
import com.example.myapplication3.viewmodel.MainViewModel
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.DisposableEffect
import com.example.myapplication3.viewmodel.LocaleChangeReceiver
import androidx.compose.foundation.Image


@Composable
fun MainScreen(viewModel: MainViewModel = viewModel(), context: Context) {
    var gdp by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    var currencyValue by remember { mutableStateOf("") }
    var poderDeCompra by remember { mutableStateOf("") }
    var showDetails by remember { mutableStateOf(false) }

    // Detectar mudanças de idioma
    DisposableEffect(Unit) {
        val receiver = LocaleChangeReceiver {
            viewModel.updateLanguage()
            gdp = ""
            population = ""
            currencyValue = ""
            poderDeCompra = ""
        }

        val intentFilter = IntentFilter(Intent.ACTION_LOCALE_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
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

            // Exibe idioma atual com ícone ao lado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = viewModel.getCountryIconResource()),
                    contentDescription = stringResource(id = R.string.country_flag),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.current_language))
            }

            // Botão "Mais informações"
            Button(onClick = {
                showDetails = !showDetails
                if (showDetails) {  // Adiciona esta parte para preencher as variáveis ao clicar
                    gdp = viewModel.getGDP()
                    population = viewModel.getPopulation()
                    currencyValue = viewModel.getCurrencyValue()
                }
            }) {
                Text(text = stringResource(id = R.string.more_info))
            }

            // Exibe as informações apenas após clicar em "Mais informações"
            if (showDetails) {
                Text(text = stringResource(id = R.string.gdp, gdp))
                Text(text = stringResource(id = R.string.population, population))
                Text(text = stringResource(id = R.string.currency_value, currencyValue))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para calcular o poder de compra
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
