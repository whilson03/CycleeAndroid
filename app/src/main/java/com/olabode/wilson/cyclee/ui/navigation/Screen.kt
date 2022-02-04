package com.olabode.wilson.cyclee.ui.navigation

sealed class Screen(val route: String) {
    object Root : Screen("cyclee_root")
    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
    object Authentication : Screen("authentication")
    object Home : Screen("home")
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")

    object Verification : AuthScreen("verification/{email}") {
        fun createRoute(email: String): String {
            return "verification/$email"
        }
    }

    object ForgotPassword : AuthScreen("forgotPassword")
    object CreateNewPasswordScreen : AuthScreen("createNewPassword")
}

sealed class MainScreen(val route: String) {
    object Feeds : MainScreen("feeds")
    object Explore : MainScreen("explore")
    object Activities : MainScreen("activities")
    object Profile : MainScreen("profile")
}
