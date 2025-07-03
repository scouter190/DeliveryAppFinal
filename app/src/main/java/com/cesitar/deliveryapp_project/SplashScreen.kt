package com.cesitar.deliveryapp_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cesitar.deliveryapp_project.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // Delay + navegación segura
    LaunchedEffect(Unit) {
        delay(3000)

        // Aquí puedes verificar si hay sesión iniciada
        val haySesion = true // por ahora solo para pruebas

        navController.navigate(
            if (haySesion) AppScreens.HomeScreen.route else AppScreens.LoginScreen.route
        ) {
            popUpTo(AppScreens.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    // Diseño visual
    SplashContent()
}

@Composable
fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo App",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bienvenido",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashContent()
}
