
package com.example.remote

import android.util.Log
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()


    RegisterScreenContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onRegisterClick = {
            val finalEmail = email.trim()
            val finalPassword = password.trim()

            if (finalEmail.isBlank() || finalPassword.isBlank()) {
                Toast.makeText(
                    context,
                    "Email và mật khẩu không được để trống",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.d("RegisterScreen", "Attempting to register with email: '$finalEmail'")
                auth.createUserWithEmailAndPassword(finalEmail, finalPassword)
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Đăng ký thành công",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack() // quay về login
                    }
                    .addOnFailureListener { exception ->
                        val message = when (exception) {
                            is FirebaseAuthUserCollisionException -> "Địa chỉ email đã được sử dụng."
                            is FirebaseAuthWeakPasswordException -> "Mật khẩu phải có ít nhất 6 ký tự."
                            is FirebaseAuthInvalidCredentialsException -> "Địa chỉ email không hợp lệ."
                            else -> "Đăng ký thất bại: ${exception.localizedMessage}"
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
            }
        },
        onLoginClick = { navController.popBackStack() }
    )
}

@Composable
fun RegisterScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Đăng ký", style = MaterialTheme.typography.headlineMedium)

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
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Đăng ký")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Đã có tài khoản? Đăng nhập",
            modifier = Modifier.clickable(onClick = onLoginClick)
        )
    }
}

@Preview(showBackground = true, name = "Register Screen - Empty")
@Composable
fun RegisterScreenPreviewEmpty() {
    RegisterScreenContent(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true, name = "Register Screen - With Input")
@Composable
fun RegisterScreenPreviewWithInput() {
    RegisterScreenContent(
        email = "android@google.com",
        password = "password",
        onEmailChange = {},
        onPasswordChange = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}
