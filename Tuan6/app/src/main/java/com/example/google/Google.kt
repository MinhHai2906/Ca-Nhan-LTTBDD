package com.example.google

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun PageGG(onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activity = context as? Activity
    
    val googleAuthClient = remember { 
        if (activity != null) GoogleAuthClient(activity) else null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.anhuth),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Smart UTH", fontWeight = FontWeight.Bold,
                        fontSize = 28.sp, color = Color.Blue,
                        modifier = Modifier.offset(y = -40.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "A simple and eficient to-do app",
                        fontSize = 18.sp, color = Color.Blue
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Welcome",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp, color = Color.Black
        )
        Text(
            text = "Ready to explore? Log in to get started.",
            fontSize = 18.sp, color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                if (googleAuthClient != null) {
                    coroutineScope.launch {

                        val user = googleAuthClient.signIn()
                        if (user != null) {
                            Toast.makeText(context, "Đăng nhập thành công: ${user.displayName}", Toast.LENGTH_SHORT).show()
                            onLoginSuccess()
                        } else {

                        }
                    }
                } else {
                    Toast.makeText(context, "Context is not an Activity", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF81C784),
            )
        ) {
            Text(text = "SIGN IN WITH GOOGLE", fontSize = 16.sp, color = Color.Black)
        }
    } // column lon
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PageGGPreview() {
    PageGG(onLoginSuccess = {})
}