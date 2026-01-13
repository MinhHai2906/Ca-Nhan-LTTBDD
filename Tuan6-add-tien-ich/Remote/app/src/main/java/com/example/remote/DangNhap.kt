
package com.example.remote

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    LoginScreenContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {
            val finalEmail = email.trim()
            val finalPassword = password.trim()

            if (finalEmail.isBlank() || finalPassword.isBlank()) {
                Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(finalEmail, finalPassword)
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Đăng nhập thành công",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate("remote") { // Điều hướng đến màn hình Remote
                            popUpTo("login") { inclusive = true } // Xóa màn hình login khỏi back stack
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Sai email hoặc mật khẩu",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        },
        onRegisterClick = { navController.navigate("register") }
    )
}

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Đăng nhập", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Đăng nhập")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Chưa có tài khoản? Đăng ký",
            modifier = Modifier.clickable(onClick = onRegisterClick)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreviewWithInput() {
    LoginScreenContent(
        email = "android@google.com",
        password = "password",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}
