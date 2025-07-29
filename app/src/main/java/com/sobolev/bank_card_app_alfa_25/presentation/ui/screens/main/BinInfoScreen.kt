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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.sobolev.bank_card_app_alfa_25.R
import com.sobolev.bank_card_app_alfa_25.domain.entitites.BinInfo


@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bin_info),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            Button(
                onClick = navigateToHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(stringResource(R.string.query_history))
            }
        },
        containerColor = Color.White
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = binInput,
                onValueChange = { newValue ->
                    val digitsOnly = newValue.filter { it.isDigit() }.take(8)
                    binInput = digitsOnly.chunked(4).joinToString(" ")
                    viewModel.clearError()
                },
                label = { Text(stringResource(R.string.input_bin)) },
                placeholder = { Text("1212 1212") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.onSearch(binInput.replace(" ", "")) },
                modifier = Modifier.fillMaxWidth(),
                enabled = binInput.replace(" ", "").length == 8 && !state.isLoading
            ) {
                Text(stringResource(R.string.search))
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 24.dp)
                )
            }


            if (state.error != null && !state.isLoading) {
                Text(
                    text = state.error ?: "",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }


            state.binInfo?.let { info ->
                BinInfoCard(info = info)
            }
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
        info.country?.let { country ->
            Text("Country: ${country.name}")

            country.latitude?.let { lat ->
                country.longitude?.let { lon ->
                    Text(
                        text = "Coordinates: $lon, $lat",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            val uri = "geo:$lat,$lon".toUri()
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            context.startActivity(intent)
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Text("Card: ${info.scheme?.uppercase() ?: "Unknown"} / ${info.type ?: "Unknown"} / ${info.brand ?: "Unknown"}")
        Text("Bank: ${info.bank?.name ?: "Unknown"}")

        Spacer(modifier = Modifier.height(4.dp))

        info.bank?.url?.let { url ->
            Text(
                text = "Website: $url",
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
                text = "Phone: $phone",
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




