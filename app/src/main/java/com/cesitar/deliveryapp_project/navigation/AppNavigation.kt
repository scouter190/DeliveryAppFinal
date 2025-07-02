package com.cesitar.deliveryapp_project.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.cesitar.deliveryapp_project.SplashScreen
import androidx.navigation.compose.composable
import com.cesitar.deliveryapp_project.MainScreen
import com.cesitar.deliveryapp_project.LoginScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
            LoginScreen()
        }
    }
}

