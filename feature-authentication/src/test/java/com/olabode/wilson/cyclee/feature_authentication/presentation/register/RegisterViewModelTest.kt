package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import com.olabode.wilson.cyclee.feature_authentication.CoroutinesTestRule
import com.olabode.wilson.cyclee.feature_authentication.ThreadExceptionHandlerTestRule
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/11/2021
 * EMAIL: whilson03@gmail.com
 */

class RegisterViewModelTest {
    private lateinit var testRobot: RegisterViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val threadExceptionHandlerTestRule = ThreadExceptionHandlerTestRule()

    private companion object {
        private val defaultCredentials = RegisterCredentials(
            firstName = "wilson",
            lastName = "Olabode",
            email = "whilson03@gmail.com",
            password = "password",
            confirmPassword = "password"
        )
        const val BLANK = ""
    }

    @Before
    fun setUp() {
        testRobot = RegisterViewModelRobot()
    }

    @Test
    fun testUpdateCredentials() = runBlockingTest {
        val credentials = defaultCredentials

        val initialState = RegisterViewState.Initial

        val firstNameEntered = RegisterViewState.Active(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
            )
        )

        val lastNameEntered = RegisterViewState.Active(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
            )
        )

        val emailEntered = RegisterViewState.Active(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
            )
        )

        val passwordEntered = RegisterViewState.Active(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
            )
        )

        val completeCredentialEnteredState = RegisterViewState.Active(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
                confirmPassword = credentials.confirmPassword

            )
        )
        val expectedViewStates = listOf(
            initialState,
            firstNameEntered,
            lastNameEntered,
            emailEntered,
            passwordEntered,
            completeCredentialEnteredState
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterFirstName(credentials.firstName)
                    enterLastName(credentials.lastName)
                    enterEmail(credentials.email)
                    enterPassword(credentials.password)
                    enterConfirmationPassword(credentials.confirmPassword)
                },
                viewStates = expectedViewStates,
            )
    }
}