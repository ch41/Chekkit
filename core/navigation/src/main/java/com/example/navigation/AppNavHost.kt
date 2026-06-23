package com.example.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui.screen.ScannerScreen
import com.example.navigation.model.AppDestinations
import com.example.ui.screen.DashboardScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home,
        modifier = modifier
    ) {
        composable<AppDestinations.Home> {
            DashboardScreen()
        }
        composable<AppDestinations.Receipts> {
            DummyScreen("Receipts")
        }
        composable<AppDestinations.Insights> {
            DummyScreen("Insights")
        }
        composable<AppDestinations.Settings> {
            DummyScreen("Settings")
        }
        composable<AppDestinations.Scanner> {
            ScannerScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun DummyScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$name Screen")
    }
}
