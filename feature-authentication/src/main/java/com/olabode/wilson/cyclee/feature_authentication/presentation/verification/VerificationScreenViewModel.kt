package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.core.utils.CycleeCountDownTimer
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import com.olabode.wilson.cyclee.networking.constants.NetworkConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@HiltViewModel
class VerificationScreenViewModel @Inject constructor(
    private val tokenVerificationUseCase: TokenVerificationUseCase
) : ViewModel() {

    val timerState = CycleeCountDownTimer(millis = (2 * 60 * 1000))

    private val _uiState: MutableStateFlow<VerificationScreenUiState> =
        MutableStateFlow(VerificationScreenUiState())
    val uiState: StateFlow<VerificationScreenUiState> = _uiState

    fun onTokenChanged(token: String) {
        val newToken = uiState.value.credentials.copy(token = token)
        _uiState.value = _uiState.value.copy(
            credentials = newToken,
            isSendButtonEnabled = newToken.token.length == AuthConstants.TOKEN_LENGTH
        )
    }

    fun onResendToken() {
        // todo
    }

    fun submitToken() {
        val enteredToken = _uiState.value.credentials
        _uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = tokenVerificationUseCase(enteredToken)
            handleTokenSubmissionResult(result)
        }
    }

    private fun handleTokenSubmissionResult(
        result: Result<String>
    ) {
        _uiState.value = when (result) {
            is Result.Success -> {
                _uiState.value.copy(
                    isLoading = false,
                    isSendButtonEnabled = false,
                    isRetryAvailable = false
                )
            }
            is Result.Error -> {
                _uiState.value.copy(
                    errorMessage = UIText.StringText(
                        result.message ?: NetworkConstants.GENERIC_FAILURE_MESSAGE,
                    ),
                    isLoading = false
                )
            }
        }
    }
}
