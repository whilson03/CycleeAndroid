package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
internal fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        addSplashGraph(navController)
        addOnBoardingGraph(navController)
    }
}
