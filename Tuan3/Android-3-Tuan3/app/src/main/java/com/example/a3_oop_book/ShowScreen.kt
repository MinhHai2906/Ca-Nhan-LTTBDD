package com.example.a3_oop_book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun Menu(content: @Composable () -> Unit) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        content()
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Quản lý", Icons.Default.Home),
    LIST("Danh sách", Icons.Default.List),
    EMPLOYEE("Nhân viên", Icons.Default.Person),
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ShowScreenPreview(modifier: Modifier = Modifier) {
    var employeeName by rememberSaveable { mutableStateOf("") }

    Menu {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hệ thống",
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
                    fontSize = 30.sp
                )
                Text(
                    text = "Quản lý sách",
                    fontSize = 30.sp
                )

                Text(
                    text = "Nhân viên",
                    modifier = Modifier.padding(top = 30.dp).fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Row(modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = employeeName,
                        onValueChange = { employeeName = it },
                        label = { Text("Tên nhân viên") },
                        modifier = Modifier.width(270.dp)
                            .height(60.dp) 
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(start = 10.dp)
                            .width(80.dp)
                        .height(50.dp),
                        shape= RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Đổi")
                    }

                }
                Text(
                    text = "Danh sách sách",
                    modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(top = 20.dp)
                        .width(100.dp)
                        .height(50.dp),
                    shape= RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Thêm",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp)
                }
            }//column
        }
    }//menu
}
