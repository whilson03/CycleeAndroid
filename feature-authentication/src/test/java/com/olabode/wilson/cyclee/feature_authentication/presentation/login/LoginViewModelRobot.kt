package com.olabode.wilson.cyclee.feature_authentication.presentation.login

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeLoginUseCase

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class LoginViewModelRobot {

    private val fakeLoginUseCase = FakeLoginUseCase()

    private lateinit var viewModel: LoginViewModel

    fun buildViewModel() = apply {
        viewModel = LoginViewModel(fakeLoginUseCase.mock)
    }

    fun mockLoginResult(
        credentials: LoginCredential,
        result: LoginResult
    ) = apply {
        fakeLoginUseCase.mockLoginResult(credentials, result)
    }

    fun enterEmail(email: String) = apply {
        viewModel.onEmailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.onPasswordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.onSubmitClicked()
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectUiStates(
        action: LoginViewModelRobot.() -> Unit,
        viewStates: List<LoginUiState>,
    ) = apply {
        viewModel.uiState.test {
            action()

            for (state in viewStates) {
                assertThat(awaitItem()).isEqualTo(state)
            }

            this.cancel()
        }
    }
}
