package com.sobolev.bank_card_app_alfa_25.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sobolev.bank_card_app_alfa_25.presentation.ui.screens.history.HistoryScreen
import com.sobolev.bank_card_app_alfa_25.presentation.ui.screens.main.BinInfoScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.BinSearch.route
    ) {
        composable(Screen.BinSearch.route) {
            BinInfoScreen(
                navigateToHistory = {
                    navController.navigate(Screen.History.route)
                }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object BinSearch : Screen("bin_search")
    data object History : Screen("history")
}

