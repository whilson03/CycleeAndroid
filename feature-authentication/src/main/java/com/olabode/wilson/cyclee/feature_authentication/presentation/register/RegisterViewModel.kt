package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/11/2021
 * EMAIL: whilson03@gmail.com
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState(credentials = RegisterCredentials.EMPTY))
    val viewState: StateFlow<RegisterUiState> = _viewState

    fun firstNameChanged(firstName: String) {
        val credentials = _viewState.value.credentials.copy(firstName = firstName)
        _viewState.value = _viewState.value.copy(
            credentials = credentials,
            firstNameError = null
        )
    }

    fun lastNameChanged(lastName: String) {
        val credentials = _viewState.value.credentials.copy(lastName = lastName)
        _viewState.value = _viewState.value.copy(
            credentials = credentials,
            lastNameError = null
        )
    }

    fun emailChanged(email: String) {
        val credentials = _viewState.value.credentials.copy(email = email)
        _viewState.value = _viewState.value.copy(
            credentials = credentials,
            emailError = null
        )
    }

    fun passwordChanged(password: String) {
        val credentials = _viewState.value.credentials.copy(password = password)
        _viewState.value = _viewState.value.copy(
            credentials = credentials,
            passwordError = null
        )
    }

    fun confirmPasswordChanged(password: String) {
        val credentials = _viewState.value.credentials.copy(confirmPassword = password)
        _viewState.value = _viewState.value.copy(
            credentials = credentials,
            confirmPasswordError = null
        )
    }

    fun registerButtonClicked() {
        val currentCredentials = _viewState.value.credentials
        _viewState.value = _viewState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = registerUseCase(currentCredentials)
            handleRegisterResult(result, currentCredentials)
        }
    }

    private fun handleRegisterResult(
        result: RegisterResult,
        currentCredentials: RegisterCredentials,
    ) {
        _viewState.value = when (result) {
            is RegisterResult.Failure.Error -> {
                Log.e("RESULT", result.errorMessage ?: "NO MESSAGE")
                _viewState.value.copy(
                    errorMessage =
                    getErrorMessageOrUnknownError(result.errorMessage),
                    isLoading = false
                )
            }

            is RegisterResult.Failure.MismatchedPassword -> {
                _viewState.value.copy(
                    errorMessage = UIText.ResourceText(R.string.err_mismatched_passwords),
                    isLoading = false
                )
            }

            is RegisterResult.Failure.EmptyCredentials -> {
                result.toRegisterUiState(currentCredentials)
            }

            is RegisterResult.Success -> {
                _viewState.value.copy(
                    credentials = RegisterCredentials.EMPTY,
                    isLoading = false,
                    registrationCompleted = true
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

private fun RegisterResult.Failure.EmptyCredentials
.toRegisterUiState(credentials: RegisterCredentials): RegisterUiState {

    return RegisterUiState(
        credentials = credentials,
        firstNameError = UIText.ResourceText(R.string.err_empty_first_name)
            .takeIf { this.emptyFirstName },

        lastNameError = UIText.ResourceText(R.string.err_empty_lasst_name)
            .takeIf { this.emptyLastName },

        emailError = UIText.ResourceText(R.string.err_empty_email)
            .takeIf { this.emptyEmail },

        passwordError = UIText.ResourceText(R.string.err_empty_password)
            .takeIf { this.emptyPassword },

        confirmPasswordError = UIText.ResourceText(R.string.err_empty_first_name)
            .takeIf { this.emptyConfirmPassword },
        isLoading = false
    )
}
