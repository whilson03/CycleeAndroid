package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.ResendVerificationTokenUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import com.olabode.wilson.cyclee.feature_authentication.utils.timer.VerificationCountDownTimer
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
    private val tokenVerificationUseCase: TokenVerificationUseCase,
    private val resendVerificationTokenUseCase: ResendVerificationTokenUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val timer: VerificationCountDownTimer
) : ViewModel() {

    val timerState = timer.timerState

    private val _uiState: MutableStateFlow<VerificationScreenUiState> =
        MutableStateFlow(VerificationScreenUiState())
    val uiState: StateFlow<VerificationScreenUiState> = _uiState

    init {
        startTimer()
        setupEmail()
    }

    fun startTimer() {
        timer.startTimer()
    }

    fun stopTimer() {
        timer.stopTimer()
    }

    private fun setupEmail() {
        val email = savedStateHandle.get<String>("email") ?: ""
        _uiState.value = _uiState.value.copy(
            credentials = VerificationCredentials(
                email = email
            ),
            email = UIText.StringText(email)
        )
    }

    fun onTokenChanged(token: String) {
        val newToken = _uiState.value.credentials.copy(token = token)
        _uiState.value = _uiState.value.copy(
            credentials = newToken,
            isSendButtonEnabled = newToken.token.length == AuthConstants.TOKEN_LENGTH
        )
    }

    fun enableResendButton() {
        _uiState.value = _uiState.value.copy(
            isResendButtonEnabled = true
        )
    }

    fun disableResendButton() {
        _uiState.value = _uiState.value.copy(
            isResendButtonEnabled = false
        )
    }

    fun onResendToken() {
        val currentCredentials = _uiState.value.credentials
        _uiState.value = uiState.value.copy(isLoading = true, isResendButtonEnabled = false)

        viewModelScope.launch {
            val result = resendVerificationTokenUseCase(currentCredentials.email)
            handleResendTokenResult(result)
        }
    }

    fun submitToken() {
        val currentCredentials = _uiState.value.credentials
        _uiState.value = uiState.value.copy(isLoading = true, isSendButtonEnabled = false)

        viewModelScope.launch {
            val credentials = VerificationCredentials(
                token = currentCredentials.token,
                email = currentCredentials.email
            )
            val result = tokenVerificationUseCase(credentials)
            handleTokenSubmissionResult(result)
        }
    }

    private fun handleTokenSubmissionResult(result: Result<String>) {
        _uiState.value = when (result) {
            is Result.Success -> {
                stopTimer()
                _uiState.value.copy(
                    isLoading = false,
                    isSendButtonEnabled = false,
                    isResendButtonEnabled = false
                )
            }
            is Result.Error -> {
                _uiState.value.copy(
                    errorMessage = UIText.StringText(
                        result.message ?: NetworkConstants.GENERIC_FAILURE_MESSAGE,
                    ),
                    isLoading = false,
                    isSendButtonEnabled = true
                )
            }
        }
    }

    private fun handleResendTokenResult(result: Result<String>) {
        _uiState.value = when (result) {
            is Result.Success -> {
                startTimer()
                _uiState.value.copy(
                    isLoading = false,
                    isResendButtonEnabled = false
                )
            }
            is Result.Error -> {
                _uiState.value.copy(
                    errorMessage = UIText.StringText(
                        result.message ?: NetworkConstants.GENERIC_FAILURE_MESSAGE,
                    ),
                    isLoading = false,
                )
            }
        }
    }
}
