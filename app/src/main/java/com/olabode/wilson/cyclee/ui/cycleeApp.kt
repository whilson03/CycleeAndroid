package com.olabode.wilson.cyclee.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.olabode.wilson.cyclee.core.utils.snackbar.SnackbarManager
import com.olabode.wilson.cyclee.ui.components.CycleeBottomNavigationBar
import com.olabode.wilson.cyclee.ui.components.CycleeSnackbar
import com.olabode.wilson.cyclee.ui.navigation.AppNavigation
import com.olabode.wilson.cyclee.ui.theme.CycleeTheme

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 03/02/2022
 * EMAIL: whilson03@gmail.com
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CycleeApp(snackbarManager: SnackbarManager) {
    CycleeTheme {
        val appState = rememberCycleeAppState(snackbarManager = snackbarManager)
        Scaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    CycleeBottomNavigationBar(
                        items = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier,
                    snackbar = { snackbarData ->
                        CycleeSnackbar(snackbarData)
                    }
                )
            }
        ) { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = appState.navController
            )
        }
    }
}
