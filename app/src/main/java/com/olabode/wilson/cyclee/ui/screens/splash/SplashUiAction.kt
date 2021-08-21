package com.olabode.wilson.cyclee.ui.screens.splash

internal sealed class SplashUiAction {
    object Home : SplashUiAction()
    object Auth : SplashUiAction()
    object OnBoard : SplashUiAction()
}
