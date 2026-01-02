package com.example.oop_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

        ) {
        Image(
            painter = painterResource(id = R.drawable.anhuth),
            contentDescription = "Home Image",
            modifier=Modifier.padding(top=230.dp).size(200.dp)
            )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "UTH SmartTasks", color = Color.Blue,
            fontSize = 32.sp, fontWeight = FontWeight.Bold
            )
        Spacer(modifier=Modifier.weight(1f))
        Button(
            onClick = { navController.navigate("page1") },
            modifier=Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom=60.dp)
                .height(60.dp),
            colors=ButtonDefaults.buttonColors(Color.Blue)
        ){
            Text(text="Next",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}
