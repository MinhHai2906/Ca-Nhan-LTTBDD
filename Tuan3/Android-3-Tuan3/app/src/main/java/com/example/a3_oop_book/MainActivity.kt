package com.example.a3_oop_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a3_oop_book.ui.theme.A3OOPBOOKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A3OOPBOOKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowScreenPreview(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

