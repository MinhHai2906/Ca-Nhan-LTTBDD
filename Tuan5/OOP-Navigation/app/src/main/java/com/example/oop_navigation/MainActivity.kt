package com.example.oop_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.oop_navigation.ui.theme.OOPNAVIGATIONTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOPNAVIGATIONTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
