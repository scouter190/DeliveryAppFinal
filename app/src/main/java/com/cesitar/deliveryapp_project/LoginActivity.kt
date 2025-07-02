package com.cesitar.deliveryapp_project
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}