package com.olabode.wilson.cyclee.feature_authentication.presentation.splash

internal sealed class SplashUiAction {
    object Home : SplashUiAction()
    object Auth : SplashUiAction()
    object OnBoard : SplashUiAction()
}
