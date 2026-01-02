package com.example.data_flow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserData(
    val email: String = "",
    val passcode: String = "",
    val password: String = "",
    val submittedData: UserData? = null
)

class DataViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserData())
    val uiState: StateFlow<UserData> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePasscode(passcode: String) {
        _uiState.value = _uiState.value.copy(passcode = passcode)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }
    
    fun submitData() {
        val currentData = _uiState.value
        _uiState.value = currentData.copy(
            submittedData = UserData(
                email = currentData.email,
                passcode = currentData.passcode,
                password = currentData.password
            )
        )
    }

    fun resetData() {

        val submitted = _uiState.value.submittedData
        _uiState.value = UserData(submittedData = submitted)
    }
}