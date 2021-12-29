package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.olabode.wilson.cyclee.feature_authentication.presentation.login.LoginScreen
import com.olabode.wilson.cyclee.feature_authentication.presentation.register.RegisterScreen
import com.olabode.wilson.cyclee.feature_authentication.presentation.resetpassword.CreateNewPasswordScreen
import com.olabode.wilson.cyclee.feature_authentication.presentation.resetpassword.RecoverPasswordScreen
import com.olabode.wilson.cyclee.feature_authentication.presentation.verification.VerificationScreen

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 05/09/2021
 * EMAIL: whilson03@gmail.com
 */
@Suppress("LongMethod")
@ExperimentalComposeUiApi
fun NavGraphBuilder.addAuthGraph(navController: NavController) {
    navigation(
        route = Screen.Authentication.route,
        startDestination = AuthScreen.Login.route
    ) {
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
                },
                onNavigateHome = {}
            )
        }
        composable(AuthScreen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToVerification = { email ->
                    navController.navigate(AuthScreen.Verification.createRoute(email)) {
                        popUpTo(AuthScreen.Login.route)
                    }
                }
            )
        }
        composable(AuthScreen.ForgotPassword.route) {
            RecoverPasswordScreen(
                onNavigateToVerification = {
                    navController.navigate(AuthScreen.Verification.route)
                }
            )
        }

        composable(
            route = AuthScreen.Verification.route,
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            VerificationScreen(
                onNavigateToLogin = {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthScreen.CreateNewPasswordScreen.route) {
            CreateNewPasswordScreen(
                onNavigateToLogin = {
                }
            )
        }
    }
}
