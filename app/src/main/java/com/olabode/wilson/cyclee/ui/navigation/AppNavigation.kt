package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@ExperimentalComposeUiApi

@Composable
internal fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        route = Screen.Root.route
    ) {
        addSplashGraph(navController)
        addOnBoardingGraph(navController)
        addAuthGraph(navController)
    }
}
