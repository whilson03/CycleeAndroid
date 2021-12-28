package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.olabode.wilson.cyclee.feature_authentication.presentation.splash.SplashScreen

@ExperimentalComposeUiApi
fun NavGraphBuilder.addSplashGraph(navController: NavController) {
    composable(route = Screen.Splash.route) {
        SplashScreen(
            openMainApp = {},
            openAuth = {
                navController.navigate(Screen.Authentication.route)
            },
            openOnBoarding = {
                navController.popBackStack()
                navController.navigate(Screen.OnBoarding.route)
            }
        )
    }
}
