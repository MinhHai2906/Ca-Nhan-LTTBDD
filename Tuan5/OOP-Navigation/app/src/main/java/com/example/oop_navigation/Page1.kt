package com.example.oop_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Page1(navController: NavHostController){
    Class3PageUi(
        pagescreen = page1(),
        onNextClick = { navController.navigate("page2") },
        onBackClick = { navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page1Preview(){
    val navController = rememberNavController()
    Page1(navController)
}