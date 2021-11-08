package com.olabode.wilson.cyclee.feature_authentication.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    openOnBoarding: () -> Unit,
    openAuth: () -> Unit,
    openMainApp: () -> Unit,
) {
    SplashScreenContent(
        openAuth = openAuth,
        openOnBoarding = openOnBoarding,
        openMainApp = openMainApp
    )
}

@Suppress("UnusedPrivateMember")
@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    openOnBoarding: () -> Unit,
    openAuth: () -> Unit,
    openMainApp: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        // This will always refer to the latest onTimeout function that
        // SplashScreen was recomposed with
        val currentOnTimeout by rememberUpdatedState(openOnBoarding)
        // todo implement proper navigation callback calling.

        // Create an effect that matches the lifecycle of SplashScreen.
        // If SplashScreen recomposes or onTimeout changes,
        // the delay shouldn't start again.
        LaunchedEffect(true) {
            delay(2000L)
            currentOnTimeout()
        }
        Text("CYCLEE")
    }
}
