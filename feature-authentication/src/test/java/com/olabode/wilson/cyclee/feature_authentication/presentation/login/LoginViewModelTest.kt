package com.olabode.wilson.cyclee.feature_authentication.presentation.login

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.CoroutinesTestRule
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.ThreadExceptionHandlerTestRule
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val threadExceptionHandlerTestRule = ThreadExceptionHandlerTestRule()

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    private companion object {
        private const val email = "email.com"
        private const val password = "password"

        private val testCredentials = LoginCredential(
            email = email,
            password = password
        )
    }

    @Test
    fun `credentials should be updated when values are entered into the input fields`() =
        runBlockingTest {

            val initialUiSate = LoginUiState(credential = LoginCredential("", ""))

            val emailEntered = LoginUiState(
                credential = LoginCredential(
                    email = email, ""
                )
            )

            val emailAndPasswordEntered = LoginUiState(
                credential = LoginCredential(
                    email = email,
                    password = password
                )
            )

            val uiStates = listOf(
                initialUiSate,
                emailEntered,
                emailAndPasswordEntered
            )

            testRobot
                .buildViewModel()
                .expectUiStates(
                    action = {
                        enterEmail(testCredentials.email)
                        enterPassword(testCredentials.password)
                    },
                    viewStates = uiStates,
                )
        }

    @Test
    fun `clicking login button when input fields are blank returns error for each field`() =
        runBlockingTest {
            val emptyCredential = LoginCredential("", "")

            val initialUiSate = LoginUiState(credential = emptyCredential)

            val submittingState = LoginUiState(
                credential = emptyCredential,
                isLoading = true
            )

            val inputsErrorState = LoginUiState(
                credential = emptyCredential,
                emailErrorMessage = UIText.ResourceText(R.string.err_empty_email),
                passwordErrorMessage = UIText.ResourceText(R.string.err_empty_password),
                isLoading = false,
            )

            val uiStates = listOf(
                initialUiSate,
                submittingState,
                inputsErrorState,
            )

            testRobot
                .buildViewModel()
                .mockLoginResult(
                    credentials = emptyCredential,
                    result = LoginResult.Failure.EmptyCredentials(true, true)
                )
                .expectUiStates(
                    action = {
                        clickLoginButton()
                    },
                    viewStates = uiStates,
                )
        }

    @Test
    fun `should return an unauthenticated error message if user account is still pending`() =
        runBlockingTest {
            val initialUiSate = LoginUiState(credential = LoginCredential("", ""))

            val emailEntered = LoginUiState(
                credential = LoginCredential(
                    email = email, ""
                )
            )

            val emailAndPasswordEntered = LoginUiState(credential = testCredentials)

            val submittingState = LoginUiState(
                credential = testCredentials,
                isLoading = true
            )

            val unVerifiedErrorState = LoginUiState(
                credential = testCredentials,
                isLoading = false,
                isUnauthenticatedUser = false,
                errorMessage = UIText.ResourceText(R.string.err_unauthenticated)
            )

            val uiStates = listOf(
                initialUiSate,
                emailEntered,
                emailAndPasswordEntered,
                submittingState,
                unVerifiedErrorState,
            )

            testRobot
                .buildViewModel()
                .mockLoginResult(
                    credentials = testCredentials,
                    result = LoginResult.Failure.UnVerifiedAccount
                )
                .expectUiStates(
                    action = {
                        enterEmail(testCredentials.email)
                        enterPassword(testCredentials.password)
                        clickLoginButton()
                    },
                    viewStates = uiStates,
                )
        }

    @Test
    fun `should return success result when login is successful`() = runBlockingTest {
        val initialUiSate = LoginUiState(credential = LoginCredential("", ""))

        val emailEntered = LoginUiState(
            credential = LoginCredential(
                email = email, ""
            )
        )

        val emailAndPasswordEntered = LoginUiState(credential = testCredentials)

        val submittingState = LoginUiState(
            credential = testCredentials,
            isLoading = true
        )

        val submittedState = LoginUiState(
            credential = testCredentials,
            isLoading = false,
            isLoginSuccessful = true
        )

        val uiStates = listOf(
            initialUiSate,
            emailEntered,
            emailAndPasswordEntered,
            submittingState,
            submittedState,
        )

        testRobot
            .buildViewModel()
            .mockLoginResult(
                credentials = testCredentials,
                result = LoginResult.Success
            )
            .expectUiStates(
                action = {
                    enterEmail(testCredentials.email)
                    enterPassword(testCredentials.password)
                    clickLoginButton()
                },
                viewStates = uiStates,
            )
    }

    @Test
    fun `should return error result when login attempt fails`() = runBlockingTest {
        val initialUiSate = LoginUiState(credential = LoginCredential("", ""))

        val emailEntered = LoginUiState(
            credential = LoginCredential(
                email = email, ""
            )
        )

        val emailAndPasswordEntered = LoginUiState(credential = testCredentials)

        val submittingState = LoginUiState(
            credential = testCredentials,
            isLoading = true
        )

        val errorResultState = LoginUiState(
            credential = testCredentials,
            isLoading = false,
            isLoginSuccessful = false,
            errorMessage = UIText.StringText("failed")
        )

        val uiStates = listOf(
            initialUiSate,
            emailEntered,
            emailAndPasswordEntered,
            submittingState,
            errorResultState,
        )

        testRobot
            .buildViewModel()
            .mockLoginResult(
                credentials = testCredentials,
                result = LoginResult.Failure.Error("failed")
            )
            .expectUiStates(
                action = {
                    enterEmail(testCredentials.email)
                    enterPassword(testCredentials.password)
                    clickLoginButton()
                },
                viewStates = uiStates,
            )
    }
}
