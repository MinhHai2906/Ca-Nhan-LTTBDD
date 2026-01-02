package com.example.oop_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Page3(navController: NavHostController){
    Class3PageUi(
        pagescreen = page3(),
        onNextClick = { navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }},
        onBackClick = { navController.navigate("page2") {
            popUpTo("page2") { inclusive = true }
        }}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page3Preview(){
    val navController = rememberNavController()
    Page3(navController)
}