package com.example.google

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

class GoogleAuthClient(
    private val activity: Activity
) {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(activity)

    suspend fun signIn(): FirebaseUser? {
        // 1. Lấy Web Client ID
        // Cố gắng lấy từ resources sinh ra
        val clientIdResId = activity.resources.getIdentifier(
            "default_web_client_id",
            "string",
            activity.packageName
        )

        var webClientId = if (clientIdResId != 0) {
            activity.getString(clientIdResId)
        } else {
            ""
        }
        
        // Nếu không tìm thấy resource, sử dụng Web Client ID từ google-services.json
        if (webClientId.isEmpty()) {
             val msg = "Lỗi: File google-services.json thiếu thông tin OAuth Client. Vui lòng Enable Google Sign-In trên Firebase Console và tải lại file config."
             Log.e("GoogleAuth", msg)
             showToast(msg)
             return null
        }

        // 2. Tạo Nonce (bắt buộc để bảo mật và tránh lỗi trên một số thiết bị)
        val nonce = UUID.randomUUID().toString()
        val hashedNonce = hashNonce(nonce)

        // 3. Cấu hình Google Sign-In
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .setNonce(hashedNonce) 
            .setAutoSelectEnabled(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
            // 4. Yêu cầu credential
            val result = credentialManager.getCredential(
                request = request,
                context = activity
            )

            // 5. Xử lý credential trả về
            if (result.credential is CustomCredential && 
                result.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
                
                val firebaseCredential = GoogleAuthProvider.getCredential(
                    googleIdTokenCredential.idToken,
                    null
                )

                val authResult = auth.signInWithCredential(firebaseCredential).await()
                
                Log.d("GoogleAuth", "Đăng nhập thành công: ${authResult.user?.email}")
                authResult.user
            } else {
                val msg = "Loại credential không hỗ trợ: ${result.credential.type}"
                Log.e("GoogleAuth", msg)
                // showToast(msg) // Không cần toast lỗi này cho user, nhưng log để debug
                null
            }

        } catch (e: GetCredentialException) {
            val errorMsg = e.message ?: "Unknown error"
            Log.e("GoogleAuth", "Sign in failed: $errorMsg")
            
            if (errorMsg.contains("16:")) {
                showToast("Lỗi cấu hình (Code 16): Kiểm tra SHA-1 fingerprint trên Firebase Console.")
            } else if (errorMsg.contains("cancelled")) {
                showToast("Đã hủy đăng nhập.")
            } else {
                showToast("Lỗi đăng nhập: $errorMsg")
            }
            null
        } catch (e: Exception) {
            Log.e("GoogleAuth", "Authentication failed: ${e.message}")
            showToast("Lỗi xác thực: ${e.message}")
            null
        }
    }
    
    fun getSignedInUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut() {
        auth.signOut()
    }

    private fun hashNonce(rawNonce: String): String {
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun showToast(message: String) {
        activity.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }
}
