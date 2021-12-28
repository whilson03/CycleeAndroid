package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeCountDownTimer
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeResendTokenVerificationUseCase
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeTokenVerificationUseCase

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 25/12/2021
 * EMAIL: whilson03@gmail.com
 */

class VerificationScreenViewModelRobot {

    private val fakeTokenVerificationUseCase = FakeTokenVerificationUseCase()

    private val fakeResendTokenVerificationUseCase = FakeResendTokenVerificationUseCase()

    private val savedStateHandle = SavedStateHandle().apply {
        set("email", "test@mail.com")
    }

    private val fakeCountDownTimer = FakeCountDownTimer()

    private lateinit var viewModel: VerificationScreenViewModel

    fun buildViewModel() = apply {
        viewModel = VerificationScreenViewModel(
            tokenVerificationUseCase = fakeTokenVerificationUseCase.mock,
            resendVerificationTokenUseCase = fakeResendTokenVerificationUseCase.mock,
            savedStateHandle = savedStateHandle,
            timer = fakeCountDownTimer,
        )
    }

    fun mockTokenVerificationResult(
        credentials: VerificationCredentials,
        result: Result<String>
    ) = apply {
        fakeTokenVerificationUseCase.mockTokenVerificationResult(
            credentials = credentials,
            result = result
        )
    }

    fun mockResendTokenVerificationResult(
        email: String,
        result: Result<String>
    ) = apply {
        fakeResendTokenVerificationUseCase.mockResendTokenVerificationResult(
            email = email,
            result = result
        )
    }

    fun resendToken() {
        viewModel.onResendToken()
    }

    fun enterToken(token: String) {
        viewModel.onTokenChanged(token)
    }

    fun submitToken() {
        viewModel.submitToken()
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectViewStates(
        action: VerificationScreenViewModelRobot.() -> Unit,
        viewStates: List<VerificationScreenUiState>,
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
