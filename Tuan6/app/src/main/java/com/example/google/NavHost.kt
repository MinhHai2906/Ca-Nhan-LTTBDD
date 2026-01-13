package com.example.google

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            PageGG(
                onLoginSuccess = {
                    navController.navigate("profile")
                }
            )
        }
        composable("profile") {
            Googledone(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
