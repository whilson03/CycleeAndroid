package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeRegisterUseCase

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/11/2021
 * EMAIL: whilson03@gmail.com
 */

class RegisterViewModelRobot {
    private val fakeRegisterUseCase = FakeRegisterUseCase()

    private lateinit var viewModel: RegisterViewModel

    fun buildViewModel() = apply {
        viewModel = RegisterViewModel(fakeRegisterUseCase.mock)
    }

    fun mockRegisterResult(
        credentials: RegisterCredentials,
        result: RegisterResult
    ) = apply {
        fakeRegisterUseCase.mockRegisterResult(credentials, result)
    }

    fun enterFirstName(firstName: String) = apply {
        viewModel.firstNameChanged(firstName)
    }

    fun enterLastName(lastName: String) = apply {
        viewModel.lastNameChanged(lastName)
    }

    fun enterEmail(email: String) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun enterConfirmationPassword(password: String) = apply {
        viewModel.confirmPasswordChanged(password)
    }

    fun clickRegisterButton() = apply {
        viewModel.registerButtonClicked()
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectViewStates(
        action: RegisterViewModelRobot.() -> Unit,
        viewStates: List<RegisterUiState>,
    ) = apply {
        viewModel.viewState.test {
            action()

            for (state in viewStates) {
                assertThat(awaitItem()).isEqualTo(state)
            }

            this.cancel()
        }
    }
}
