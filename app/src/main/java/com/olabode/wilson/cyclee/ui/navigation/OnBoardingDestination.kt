package com.olabode.wilson.cyclee.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.olabode.wilson.cyclee.feature_onboarding.presentation.onboarding.OnBoardingScreen

@Suppress("UnusedPrivateMember")
fun NavGraphBuilder.addOnBoardingGraph(navController: NavController) {
    composable(route = Screen.OnBoarding.route) {
        OnBoardingScreen(
            openAuthScreen = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.route)
            }
        )
    }
}
