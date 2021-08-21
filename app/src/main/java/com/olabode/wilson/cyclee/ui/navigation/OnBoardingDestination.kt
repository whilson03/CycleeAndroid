package com.olabode.wilson.cyclee.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.olabode.wilson.cyclee.ui.onboarding.OnBoardingScreen

@Suppress("UnusedPrivateMember")
@ExperimentalPagerApi
fun NavGraphBuilder.addOnBoardingGraph(navController: NavController) {
    composable(route = Screen.OnBoarding.route) {
        OnBoardingScreen(
            openAuthScreen = {}
        )
    }
}
