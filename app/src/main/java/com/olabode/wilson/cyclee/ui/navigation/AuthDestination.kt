package com.olabode.wilson.cyclee.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 05/09/2021
 * EMAIL: whilson03@gmail.com
 */
@Suppress("UnusedPrivateMember")
fun NavGraphBuilder.addAuthGraph(navController: NavController) {
    navigation(route = Screen.Authentication.route, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {}
        composable(route = AuthScreen.Register.route) {}
        composable(route = AuthScreen.ForgotPassword.route) {}
        composable(route = AuthScreen.Verification.route) {}
    }
}
