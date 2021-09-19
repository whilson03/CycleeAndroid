package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.olabode.wilson.cyclee.ui.screens.login.LoginScreen
import com.olabode.wilson.cyclee.ui.screens.register.RegisterScreen
import com.olabode.wilson.cyclee.ui.screens.verification.VerificationScreen

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 05/09/2021
 * EMAIL: whilson03@gmail.com
 */
@ExperimentalComposeUiApi
fun NavGraphBuilder.addAuthGraph(navController: NavController) {
    navigation(route = Screen.Authentication.route, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onNavigateToVerification = {
                    navController.navigate(AuthScreen.Verification.route)
                },
                onNavigateToRegister = {
                    navController.navigate(AuthScreen.Register.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(AuthScreen.ForgotPassword.route)
                }
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToVerification = {
                    navController.navigate(AuthScreen.Verification.route)
                }
            )
        }
        composable(route = AuthScreen.ForgotPassword.route) {
        }
        composable(route = AuthScreen.Verification.route) {
            VerificationScreen()
        }
    }
}
