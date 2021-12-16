package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 19/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    onNavigateToVerification: () -> Unit
) {
    val scrollState = rememberScrollState()
    val viewModel: RegisterViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState()

    RegisterScreenContent(
        modifier = modifier,
        scrollState = scrollState,
        onNavigateToLogin = onNavigateToLogin,
        viewState = viewState.value,
        viewModel = hiltViewModel()
    )
}
