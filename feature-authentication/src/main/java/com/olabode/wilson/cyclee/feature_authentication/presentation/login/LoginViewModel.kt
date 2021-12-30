package com.olabode.wilson.cyclee.feature_authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(email: String) {
        val credential = _uiState.value.credential.copy(email = email)
        _uiState.value = _uiState.value.copy(
            credential = credential,
            emailErrorMessage = null
        )
    }

    fun onPasswordChanged(password: String) {
        val credential = _uiState.value.credential.copy(password = password)
        _uiState.value = _uiState.value.copy(
            credential = credential,
            passwordErrorMessage = null
        )
    }

    fun onSubmitClicked() {
        val currentCredentials = _uiState.value.credential
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val result = loginUseCase(currentCredentials)
            handleLoginResult(result, currentCredentials)
        }
    }

    private fun handleLoginResult(result: LoginResult, credential: LoginCredential) {

        _uiState.value = when (result) {
            is LoginResult.Failure.Error -> {
                _uiState.value.copy(
                    errorMessage =
                    getErrorMessageOrUnknownError(result.errorMessage),
                    isLoading = false
                )
            }

            is LoginResult.Failure.UnVerifiedAccount -> {
                _uiState.value.copy(
                    errorMessage = UIText.ResourceText(R.string.err_unauthenticated),
                    isLoading = false
                )
            }

            is LoginResult.Failure.EmptyCredentials -> {
                result.toLoginUiState(credential)
            }

            is LoginResult.Success -> {
                _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccessful = true
                )
            }
        }
    }

    private fun getErrorMessageOrUnknownError(message: String?): UIText {
        return if (message == null) {
            UIText.ResourceText(R.string.err_unknown)
        } else {
            UIText.StringText(message)
        }
    }
}

private fun LoginResult.Failure.EmptyCredentials.toLoginUiState(credential: LoginCredential): LoginUiState {

    return LoginUiState(
        credential = credential,
        emailErrorMessage = UIText.ResourceText(R.string.err_empty_email)
            .takeIf { this.emptyEmail },

        passwordErrorMessage = UIText.ResourceText(R.string.err_empty_password)
            .takeIf { this.emptyPassword },
        isLoading = false
    )
}
