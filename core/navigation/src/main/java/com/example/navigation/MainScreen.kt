package com.example.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.design.component.ChekkitBottomBar
import com.example.navigation.model.AppDestinations

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedIndex = when {
        currentDestination?.hasRoute<AppDestinations.Home>() == true -> 0
        currentDestination?.hasRoute<AppDestinations.Receipts>() == true -> 1
        currentDestination?.hasRoute<AppDestinations.Insights>() == true -> 2
        currentDestination?.hasRoute<AppDestinations.Settings>() == true -> 3
        else -> 0
    }

    Scaffold(
        bottomBar = {
            ChekkitBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    val destination = when (index) {
                        0 -> AppDestinations.Home
                        1 -> AppDestinations.Receipts
                        2 -> AppDestinations.Insights
                        3 -> AppDestinations.Settings
                        else -> AppDestinations.Home
                    }
                    navController.navigate(destination) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onCameraClick = {
                    navController.navigate(AppDestinations.Scanner)
                }
            )
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
