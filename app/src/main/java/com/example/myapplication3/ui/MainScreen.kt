package com.example.myapplication3.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication3.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    // Usa o estado da ViewModel
    val language = viewModel.currentLanguage
    val greetingMessage = viewModel.greetingMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Texto indicando o idioma atual
        Text(text = "Current Language: $language")

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de saudação
        Text(text = greetingMessage)

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para mudar a saudação (exemplo básico)
        Button(onClick = {
            viewModel.setGreetingMessage("Hello from Compose!")
        }) {
            Text("Change Greeting")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val viewModel = MainViewModel().apply {
        setGreetingMessage("Preview Greeting")
    }
    MainScreen(viewModel)
}
