package com.example.oop_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Page2(navController: NavHostController){
    Class3PageUi(
        pagescreen = page2(),
        onNextClick = { navController.navigate("page3") },
        onBackClick = { navController.navigate("page1") {
            popUpTo("page1") { inclusive = true }
        }}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page2Preview(){
    val navController = rememberNavController()
    Page2(navController)
}