package com.olabode.wilson.cyclee.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
}
