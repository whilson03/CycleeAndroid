package com.olabode.wilson.cyclee.feature_authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 25/10/2021
 * EMAIL: whilson03@gmail.com
 */

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToVerification: (email: String) -> Unit
) {

    val viewModel: LoginViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    val currentOnNavigateToVerification by rememberUpdatedState(onNavigateToVerification)
    val currentOnNavigateHome by rememberUpdatedState(onNavigateHome)

    LaunchedEffect(uiState.value) {
        if (uiState.value.isUnauthenticatedUser) {
            val email = uiState.value.credential.email
            currentOnNavigateToVerification(email)
        }

        if (uiState.value.isLoginSuccessful) {
            currentOnNavigateHome()
        }
    }

    LoginScreenContent(
        modifier = modifier,
        uiState = uiState.value,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onSubmitForm = viewModel::onSubmitClicked,
        onForgotPasswordClicked = onNavigateToForgotPassword,
        onNavigateToRegister = onNavigateToRegister
    )
}
