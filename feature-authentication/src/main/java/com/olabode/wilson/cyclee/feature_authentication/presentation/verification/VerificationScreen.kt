package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/09/2021
 * EMAIL: whilson03@gmail.com
 */
@ExperimentalComposeUiApi
@Composable
fun VerificationScreen() {

    val viewModel: VerificationScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    VerificationScreenContent(
        uiState = uiState.value,
        isResendButtonEnabled = uiState.value.isRetryAvailable,
        isSubmitButtonEnabled = uiState.value.isSendButtonEnabled,
        onTokenChanged = viewModel::onTokenChanged,
        onResendClicked = viewModel::onResendToken,
        onSubmitClicked = viewModel::submitToken
    )
}
