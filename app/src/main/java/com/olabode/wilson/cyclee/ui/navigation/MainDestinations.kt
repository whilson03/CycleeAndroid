package com.olabode.wilson.cyclee.ui.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
fun NavGraphBuilder.addMainGraph(navController: NavController) {
    navigation(
        route = Screen.Home.route,
        startDestination = MainScreen.Activities.route
    ) {
        composable(route = MainScreen.Feeds.route) {
            TODO("add feeds screen")
        }

        composable(route = MainScreen.Explore.route) {
            TODO("add explore screen")
        }

        composable(route = MainScreen.Activities.route) {
            TODO("add activities screen")
        }

        composable(route = MainScreen.Profile.route) {
            TODO("add profile screen")
        }
    }
}
