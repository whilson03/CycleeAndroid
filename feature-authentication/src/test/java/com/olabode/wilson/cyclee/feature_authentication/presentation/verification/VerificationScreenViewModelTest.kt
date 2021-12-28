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
    fun testUpdateTokenCredential() = runBlockingTest {
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
    fun testIncompleteTokenInputDisablesSubmitButton() = runBlockingTest {
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
    fun testSuccessfulCredentialSubmission() = runBlockingTest {
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
            email = uiEmail
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
    fun testCredentialSubmissionError() = runBlockingTest {
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
}
