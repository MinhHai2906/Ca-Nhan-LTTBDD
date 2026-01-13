package com.example.remote

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

private const val TAG = "RemoteScreen"
private const val WELCOME_MESSAGE_KEY = "Remote2"

@Composable
fun RemoteScreen() {
    val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    var welcomeMessage by remember { mutableStateOf("hello") } 

    DisposableEffect(LocalLifecycleOwner.current) {
        Log.e(TAG, "!!! DisposableEffect ĐÃ BẮT ĐẦU !!! Bạn có thấy dòng này trong Logcat không?")

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(mapOf(WELCOME_MESSAGE_KEY to "hello"))

        val fetchAndActivateTask = remoteConfig.fetchAndActivate()
        
        fetchAndActivateTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d(TAG, "Cập nhật thành công: $updated")
                welcomeMessage = remoteConfig.getString(WELCOME_MESSAGE_KEY)
            } else {
                Log.e(TAG, "!!! LẤY DỮ LIỆU THẤT BẠI !!!", task.exception)
            }
        }

        onDispose {
            Log.e(TAG, "!!! DisposableEffect ĐÃ KẾT THÚC. Màn hình đang đóng. !!!")
        }
    }

    RemoteScreenContent(text = welcomeMessage)
}

@Composable
fun RemoteScreenContent(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun RemoteScreenPreview() {
    RemoteScreenContent(text = "Noi Dung Da Thay Doi")
}
