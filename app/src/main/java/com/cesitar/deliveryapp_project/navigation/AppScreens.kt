package com.cesitar.deliveryapp_project.navigation

sealed class AppScreens(val route: String){
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object MainScreen: AppScreens("main_screen")
    object HomeScreen: AppScreens("home_screen")
    object DrawerScreen: AppScreens("drawer_screen")
}