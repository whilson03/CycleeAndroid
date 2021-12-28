package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
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
fun VerificationScreen() {

    val viewModel: VerificationScreenViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val timerState = viewModel.timerState.collectAsState()

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
