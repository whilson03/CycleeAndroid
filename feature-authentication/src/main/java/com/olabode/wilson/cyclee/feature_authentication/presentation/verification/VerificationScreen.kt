package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.utils.timer.CycleeTimerState

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/09/2021
 * EMAIL: whilson03@gmail.com
 */
@ExperimentalComposeUiApi
@Composable
fun VerificationScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
) {

    val viewModel: VerificationScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val timerState = viewModel.timerState.collectAsState()

    val currentOnNavigateToLogin by rememberUpdatedState(onNavigateToLogin)
    LaunchedEffect(uiState.value) {
        if (uiState.value.isVerificationSuccessful) {
            currentOnNavigateToLogin()
        }
    }

    val resendButtonText: UIText = when (timerState.value) {
        is CycleeTimerState.Ticking -> {
            val timeText = (timerState.value as CycleeTimerState.Ticking).formattedString
            UIText.StringText(
                LocalContext.current.getString(
                    R.string.resend_code_with_time,
                    timeText
                )
            )
        }
        CycleeTimerState.Finished -> {
            viewModel.enableResendButton()
            UIText.ResourceText(R.string.resend_code)
        }
        CycleeTimerState.Idle -> {
            UIText.ResourceText(R.string.resend_code)
        }
    }

    VerificationScreenContent(
        modifier = modifier,
        uiState = uiState.value,
        resendButtonText = resendButtonText,
        isLoading = uiState.value.isLoading,
        isResendButtonEnabled = uiState.value.isResendButtonEnabled,
        isSubmitButtonEnabled = uiState.value.isSendButtonEnabled,
        onTokenChanged = viewModel::onTokenChanged,
        onResendClicked = viewModel::onResendToken,
        onSubmitClicked = viewModel::submitToken
    )
}
