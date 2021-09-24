package com.olabode.wilson.cyclee.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
    object Authentication : Screen("authentication")
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
    object Verification : AuthScreen("verification")
    object ForgotPassword : AuthScreen("forgotPassword")
    object CreateNewPasswordScreen : AuthScreen("createNewPassword")
}
