package com.example.math03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.math03.ui.theme.Math03Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Math03Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Composable chính, quản lý toàn bộ trạng thái và logic
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val calculate: (String) -> Unit = { operation ->
        val num1 = number1.toDoubleOrNull() ?: 0.0
        val num2 = number2.toDoubleOrNull() ?: 0.0

        result = when (operation) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Error: Divide by zero"
            else -> ""
        }
    }


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Thực hành 03",
            color = Color.Blue,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 1. Ô nhập liệu thứ nhất
        MyNumberInput(
            value = number1,
            onValueChange = { number1 = it },
            label = "Number 1"
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 2. Hàng các nút tính toán
        Boxx(onOperationClick = calculate)

        Spacer(modifier = Modifier.height(15.dp))

        // 3. Ô nhập liệu thứ hai
        MyNumberInput(
            value = number2,
            onValueChange = { number2 = it },
            label = "Number 2"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 4. Kết quả
        Results(result = result)
    }
}

@Composable
fun MyButton(text: String, onClick: () -> Unit, color: Color) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(80.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            color = Color.White
        )
    }
}

@Composable
fun MyNumberInput(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Blue
        ),
        shape = RoundedCornerShape(15.dp)
    )
}

@Composable
fun Boxx(onOperationClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MyButton(text = "+", onClick = { onOperationClick("+") }, color = Color.Red)
        MyButton(text = "-", onClick = { onOperationClick("-") }, color = Color.Yellow)
        MyButton(text = "*", onClick = { onOperationClick("*") }, color = Color.Black)
        MyButton(text = "/", onClick = { onOperationClick("/") }, color = Color.LightGray)
    }
}

@Composable
fun Results(result: String) {
    Text(
        text = "Kết quả : $result",
        fontSize = 23.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(start = 30.dp),
        textAlign = TextAlign.Start
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Math03Theme {
        Greeting()
    }
}
