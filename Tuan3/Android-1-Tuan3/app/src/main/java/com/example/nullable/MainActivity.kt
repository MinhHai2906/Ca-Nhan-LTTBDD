package com.example.nullable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nullable.ui.theme.NullAbleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NullAbleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf<String?>(null) }
    var age by remember { mutableStateOf<String?>(null) }
    var showname by remember { mutableStateOf<String?>(null) }
    var showage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nguyễn Minh Hải",
                fontSize = 25.sp, color = Color.Blue
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = name ?: "",
                onValueChange = { name = it.ifBlank { null } },
                label = { Text("Nhập tên của bạn") },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = age ?: "",
                onValueChange = { age = it.ifBlank { null } },
                label = { Text("Nhập tuổi của bạn") },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Blue
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    showname = name ?: "Bạn chưa nhập tên"
                    showage = age ?: "Bạn chưa nhập tuổi"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "Xác nhận",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Tên: ${showname ?: ""}\n\nTuổi: ${showage ?: ""}",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )

        } //column
    } //box
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    NullAbleTheme {
        Greeting()
    }
}