package com.cesitar.deliveryapp_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cesitar.deliveryapp_project.navigation.AppNavigation
import com.cesitar.deliveryapp_project.ui.theme.DeliveryApp_projectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryApp_projectTheme {
                AppNavigation()
                }
            }
        }
    }


