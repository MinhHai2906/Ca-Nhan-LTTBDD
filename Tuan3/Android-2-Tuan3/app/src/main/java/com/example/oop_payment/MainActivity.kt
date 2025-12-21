package com.example.oop_payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.oop_payment.ui.theme.OOPPaymentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOPPaymentTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    PaymentScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
