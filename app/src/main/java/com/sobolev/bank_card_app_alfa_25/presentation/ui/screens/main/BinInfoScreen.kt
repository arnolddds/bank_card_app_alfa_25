package com.sobolev.bank_card_app_alfa_25.presentation.ui.screens.main

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo
import androidx.core.net.toUri


@Composable
fun BinInfoScreen(
    viewModel: BinInfoViewModel = hiltViewModel(),
    navigateToHistory: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    var binInput by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = binInput,
            onValueChange = { binInput = it },
            label = { Text("Введите BIN") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.onSearch(binInput) },
            modifier = Modifier.fillMaxWidth(),
            enabled = binInput.length in 6..8 && !state.isLoading
        ) {
            Text("Поиск")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        state.binInfo?.let { info ->
            BinInfoCard(info)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = navigateToHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("История запросов")
        }
    }
}

@Composable
fun BinInfoCard(info: BinInfo) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text("Страна: ${info.country?.name}")

        Text(
            text = "Координаты: ${info.country?.longitude}, ${info.country?.latitude}",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                val uri = "geo:${info.country?.latitude},${info.country?.longitude}".toUri()
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            },
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Тип карты: ${info.scheme?.uppercase()} / ${info.type ?: "N/A"} / ${info.brand ?: "N/A"}")
        Text("Банк: ${info.bank?.name} (${info.bank?.city})")

        Spacer(modifier = Modifier.height(4.dp))

        info.bank?.url?.let { url ->
            Text(
                text = "Сайт: $url",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, "https://$url".toUri())
                    context.startActivity(intent)
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }

        info.bank?.phone?.let { phone ->
            Text(
                text = "Телефон: $phone",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL, "tel:$phone".toUri())
                    context.startActivity(intent)
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



