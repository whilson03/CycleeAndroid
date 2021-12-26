package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeTokenVerificationUseCase

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 25/12/2021
 * EMAIL: whilson03@gmail.com
 */

class VerificationScreenViewModelRobot {

    private val fakeTokenVerificationUseCase = FakeTokenVerificationUseCase()

    private lateinit var viewModel: VerificationScreenViewModel

    fun buildViewModel() = apply {
        viewModel = VerificationScreenViewModel(fakeTokenVerificationUseCase.mock)
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
