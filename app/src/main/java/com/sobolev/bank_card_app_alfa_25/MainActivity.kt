package com.sobolev.bank_card_app_alfa_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sobolev.bank_card_app_alfa_25.ui.theme.Bank_card_app_alfa_25Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bank_card_app_alfa_25Theme {
            }
        }
    }
}
