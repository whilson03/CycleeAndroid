package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.CoroutinesTestRule
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
import com.olabode.wilson.cyclee.networking.constants.NetworkConstants
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 25/12/2021
 * EMAIL: whilson03@gmail.com
 */

class VerificationScreenViewModelTest {

    private lateinit var testRobot: VerificationScreenViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        testRobot = VerificationScreenViewModelRobot()
    }

    @Test
    fun testUpdateToken() = runBlockingTest {
        val token = VerificationToken("12345")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = true
        )

        val viewStates = listOf(initialState, tokenEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(token.token)
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun testIncompleteTokenInputDisablesSubmitButton() = runBlockingTest {
        val token = VerificationToken("123")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = false
        )

        val viewStates = listOf(initialState, tokenEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(token.token)
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun testSuccessfulTokenSubmission() = runBlockingTest {
        val token = VerificationToken("12345")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = true
        )

        val submittingState = VerificationScreenUiState(
            token = token,
            isLoading = true,
            isSendButtonEnabled = true

        )

        val submittedState = VerificationScreenUiState(
            token = token,
            isLoading = false,
            isRetryAvailable = false,
            isSendButtonEnabled = false
        )

        val viewStates = listOf(
            initialState,
            tokenEnteredState,
            submittingState,
            submittedState
        )

        testRobot
            .buildViewModel()
            .mockTokenVerificationResult(
                token = token,
                result = Result.Success("")
            )
            .expectViewStates(
                action = {
                    enterToken(token.token)
                    submitToken()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun testTokenSubmissionError() = runBlockingTest {
        val token = VerificationToken("12345")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = true
        )

        val submittingState = VerificationScreenUiState(
            token = token,
            isLoading = true,
            isSendButtonEnabled = true

        )

        val submittedStateError = VerificationScreenUiState(
            token = token,
            isLoading = false,
            isSendButtonEnabled = true,
            errorMessage = UIText.StringText(
                NetworkConstants.GENERIC_FAILURE_MESSAGE
            )
        )

        val viewStates = listOf(
            initialState,
            tokenEnteredState,
            submittingState,
            submittedStateError
        )

        testRobot
            .buildViewModel()
            .mockTokenVerificationResult(
                token = token,
                result = Result.Error()
            )
            .expectViewStates(
                action = {
                    enterToken(token.token)
                    submitToken()
                },
                viewStates = viewStates,
            )
    }
}
