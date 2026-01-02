package com.example.oop_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("page1") {
            Page1(navController)
        }
        composable("page2") {
            Page2(navController)
        }
        composable("page3") {
            Page3(navController)
        }
    }
}