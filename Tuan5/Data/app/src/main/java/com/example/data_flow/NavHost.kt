package com.example.data_flow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(viewModel: DataViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "page1") {
        composable("page1") {
            Box {
                Class4PageUi(
                    pagescreen = page1(),
                    onNextClick = { navController.navigate("page2") },
                    currentEmail = uiState.email,
                    onEmailChange = { viewModel.updateEmail(it) }
                )

                uiState.submittedData?.let { submittedData ->
                    Column(modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp)) {
                        Text("Thông tin vừa nhập:")
                        Text("Email: ${submittedData.email}")
                        Text("Passcode: ${submittedData.passcode}")
                        Text("Password: ${submittedData.password}")
                    }
                }
            }
        }
        composable("page2") {
            Class4PageUi(
                pagescreen = page2(),
                onNextClick = { navController.navigate("page3") },
                onBackClick = { navController.popBackStack() },
                // Data Binding
                currentPasscode = uiState.passcode,
                onPasscodeChange = { viewModel.updatePasscode(it) }
            )
        }
        composable("page3") {
            Class4PageUi(
                pagescreen = page3(),
                onNextClick = { navController.navigate("page4") },
                onBackClick = { navController.popBackStack() },

                currentPassword = uiState.password,
                onPasswordChange = { viewModel.updatePassword(it) }
            )
        }
        composable("page4") {
            Class4PageUi(
                pagescreen = page4(),
                onNextClick = {

                    viewModel.submitData()

                    viewModel.resetData() 
                    

                    navController.navigate("page1") {
                        popUpTo("page1") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() },

                currentEmail = uiState.email,
                currentPasscode = uiState.passcode,
                currentPassword = uiState.password
            )
        }
    }
}