package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.CoroutinesTestRule
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
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

    private val testEmail = "test@mail.com"
    private val uiEmail = UIText.StringText(testEmail)

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        testRobot = VerificationScreenViewModelRobot()
    }

    @Test
    fun `should update token value when values are entered into the pin view`() = runBlockingTest {
        val credential = VerificationCredentials(token = "12345", email = testEmail)

        // email is passed from register viewModel to the savedStateHandle of VerificationViewModel
        val initialState = VerificationScreenUiState(
            credentials = VerificationCredentials(email = testEmail),
            email = uiEmail
        )

        val credentialEnteredState = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isSendButtonEnabled = true
        )

        val viewStates = listOf(initialState, credentialEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(credential.token)
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should disable submit button when the token entered is not complete`() = runBlockingTest {
        val credential = VerificationCredentials(token = "123", email = testEmail)

        val initialState = VerificationScreenUiState(
            credentials = VerificationCredentials(email = testEmail),
            email = uiEmail
        )

        val credentialEnteredState = VerificationScreenUiState(
            credentials = credential,
            isSendButtonEnabled = false,
            email = uiEmail
        )

        val viewStates = listOf(initialState, credentialEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(credential.token)
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should return success result when verification is successful`() = runBlockingTest {
        val credential = VerificationCredentials(token = "12345", email = testEmail)

        val initialState = VerificationScreenUiState(
            credentials = VerificationCredentials(email = testEmail),
            email = uiEmail
        )

        val credentialEnteredState = VerificationScreenUiState(
            credentials = credential,
            isSendButtonEnabled = true,
            email = uiEmail
        )

        val submittingState = VerificationScreenUiState(
            credentials = credential,
            isLoading = true,
            isSendButtonEnabled = false,
            email = uiEmail
        )

        val submittedState = VerificationScreenUiState(
            credentials = credential,
            isLoading = false,
            isResendButtonEnabled = false,
            isSendButtonEnabled = false,
            email = uiEmail,
            isVerificationSuccessful = true
        )

        val viewStates = listOf(
            initialState,
            credentialEnteredState,
            submittingState,
            submittedState
        )

        testRobot
            .buildViewModel()
            .mockTokenVerificationResult(
                credentials = credential,
                result = Result.Success("")
            )
            .expectViewStates(
                action = {
                    enterToken(credential.token)
                    submitToken()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should return error result when verification fails`() = runBlockingTest {
        val credential = VerificationCredentials(token = "12345", email = testEmail)

        val initialState = VerificationScreenUiState(
            credentials = VerificationCredentials(email = testEmail),
            email = uiEmail
        )

        val credentialEnteredState = VerificationScreenUiState(
            credentials = credential,
            isSendButtonEnabled = true,
            email = uiEmail
        )

        val submittingState = VerificationScreenUiState(
            credentials = credential,
            isLoading = true,
            isSendButtonEnabled = false,
            email = uiEmail
        )

        val submittedStateError = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isLoading = false,
            isSendButtonEnabled = true,
            errorMessage = UIText.StringText(
                NetworkConstants.GENERIC_FAILURE_MESSAGE
            )
        )

        val viewStates = listOf(
            initialState,
            credentialEnteredState,
            submittingState,
            submittedStateError
        )

        testRobot
            .buildViewModel()
            .mockTokenVerificationResult(
                credentials = credential,
                result = Result.Error()
            )
            .expectViewStates(
                action = {
                    enterToken(credential.token)
                    submitToken()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should return success result when token is successfully resent`() = runBlockingTest {
        val credential = VerificationCredentials(token = "", email = testEmail)

        val initialState = VerificationScreenUiState(credentials = credential, email = uiEmail)

        val resendingTokenState = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isLoading = true
        )

        val tokenResentState = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isLoading = false
        )

        val uiStates = listOf(
            initialState,
            resendingTokenState,
            tokenResentState
        )

        testRobot
            .buildViewModel()
            .mockResendTokenVerificationResult(
                email = testEmail,
                result = Result.Success("")
            ).expectViewStates(
                action = {
                    resendToken()
                },
                viewStates = uiStates,
            )
    }

    @Test
    fun `should return error result when token fails to resend`() = runBlockingTest {
        val credential = VerificationCredentials(token = "", email = testEmail)

        val initialState = VerificationScreenUiState(credentials = credential, email = uiEmail)

        val resendingTokenState = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isLoading = true
        )

        val tokenResentState = VerificationScreenUiState(
            credentials = credential,
            email = uiEmail,
            isLoading = false,
            errorMessage = UIText.StringText(NetworkConstants.GENERIC_FAILURE_MESSAGE)
        )

        val uiStates = listOf(
            initialState,
            resendingTokenState,
            tokenResentState
        )

        testRobot
            .buildViewModel()
            .mockResendTokenVerificationResult(
                email = testEmail,
                result = Result.Error()
            ).expectViewStates(
                action = {
                    resendToken()
                },
                viewStates = uiStates,
            )
    }
}
