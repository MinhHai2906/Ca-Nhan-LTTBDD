package com.example.remote_config

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remote_config.ui.theme.RemoteconfigTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RemoteconfigTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNav(auth)
                }
            }
        }
    }
}

@Composable
fun AppNav(auth: FirebaseAuth) {
    val navController = rememberNavController()

    // Check if the user is already logged in
    val startDestination = if (auth.currentUser != null) "main" else "dangNhap"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("dangNhap") {
            DangNhap(navController, auth)
        }
        composable("dangKi") {
            DangKi(navController, auth)
        }
        composable("main") {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Main Screen")
    }
}
