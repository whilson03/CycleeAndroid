package com.olabode.wilson.cyclee.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.olabode.wilson.cyclee.utils.Constants
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        // This will always refer to the latest onTimeout function that
        // SplashScreen was recomposed with
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        // Create an effect that matches the lifecycle of SplashScreen.
        // If SplashScreen recomposes or onTimeout changes,
        // the delay shouldn't start again.
        LaunchedEffect(true) {
            delay(Constants.SplashWaitTime)
            currentOnTimeout()
        }
        Text("CYCLEE")
    }
}
