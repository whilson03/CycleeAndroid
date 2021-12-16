package com.olabode.wilson.cyclee.feature_authentication.presentation.register

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

    private val _viewState: MutableStateFlow<RegisterViewState> =
        MutableStateFlow(RegisterViewState.Initial)
    val viewState: StateFlow<RegisterViewState> = _viewState

    fun firstNameChanged(firstName: String) {
        val currentCredentials = _viewState.value.credentials
        val currentFirstNameErrorMessage =
            (_viewState.value as? RegisterViewState.Active)?.firstNameInputErrorMessage

        _viewState.value = RegisterViewState.Active(
            credentials = currentCredentials.copy(firstName = firstName),
            firstNameInputErrorMessage = currentFirstNameErrorMessage,
        )
    }

    fun lastNameChanged(lastName: String) {
        val currentCredentials = _viewState.value.credentials
        val currentLastNameErrorMessage =
            (_viewState.value as? RegisterViewState.Active)?.lastNameInputErrorMessage

        _viewState.value = RegisterViewState.Active(
            credentials = currentCredentials.copy(lastName = lastName),
            lastNameInputErrorMessage = currentLastNameErrorMessage,
        )
    }

    fun emailChanged(email: String) {
        val currentCredentials = _viewState.value.credentials
        val currentEmailErrorMessage =
            (_viewState.value as? RegisterViewState.Active)?.emailInputErrorMessage

        _viewState.value = RegisterViewState.Active(
            credentials = currentCredentials.copy(email = email),
            emailInputErrorMessage = currentEmailErrorMessage,
        )
    }

    fun passwordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials
        val currentPasswordErrorMessage =
            (_viewState.value as? RegisterViewState.Active)?.passwordInputErrorMessage

        _viewState.value = RegisterViewState.Active(
            credentials = currentCredentials.copy(password = password),
            passwordInputErrorMessage = currentPasswordErrorMessage,
        )
    }

    fun confirmPasswordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials
        val currentConfirmPasswordErrorMessage =
            (_viewState.value as? RegisterViewState.Active)?.confirmPasswordInputErrorMessage

        _viewState.value = RegisterViewState.Active(
            credentials = currentCredentials.copy(confirmPassword = password),
            confirmPasswordInputErrorMessage = currentConfirmPasswordErrorMessage,
        )
    }

    fun registerButtonClicked() {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = RegisterViewState.Submitting(
            credentials = currentCredentials,
        )

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
            is RegisterResult.Failure.InvalidCredentials -> {
                RegisterViewState.SubmissionError(
                    credentials = currentCredentials,
                    errorMessage = UIText.StringText(""), // TODO
                )
            }
            is RegisterResult.Failure.Unknown -> {
                RegisterViewState.SubmissionError(
                    credentials = currentCredentials,
                    errorMessage = UIText.StringText(""), // TODO
                )
            }
            is RegisterResult.Failure.EmptyCredentials -> {
                result.toRegisterViewState(currentCredentials)
            }
            is RegisterResult.Success -> {
                viewModelScope.launch {
                }
                RegisterViewState.Completed
            }
        }
    }
}

private fun RegisterResult.Failure.EmptyCredentials
        .toRegisterViewState(credentials: RegisterCredentials): RegisterViewState {

    return RegisterViewState.Active(
        credentials = credentials,
        firstNameInputErrorMessage = UIText.ResourceText(R.string.err_empty_first_name)
            .takeIf { this.emptyFirstName },

        lastNameInputErrorMessage = UIText.ResourceText(R.string.err_empty_lasst_name)
            .takeIf { this.emptyLastName },

        emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email)
            .takeIf { this.emptyEmail },

        passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password)
            .takeIf { this.emptyPassword },

        confirmPasswordInputErrorMessage = UIText.ResourceText(R.string.err_empty_first_name)
            .takeIf { this.emptyConfirmPassword }
    )
}
