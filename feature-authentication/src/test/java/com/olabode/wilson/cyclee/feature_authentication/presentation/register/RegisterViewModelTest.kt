package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.CoroutinesTestRule
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.ThreadExceptionHandlerTestRule
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/11/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("LongMethod")
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
    }

    @Before
    fun setUp() {
        testRobot = RegisterViewModelRobot()
    }

    @Test
    fun `credentials should be updated when values are entered into the input fields`() = runBlockingTest {
        val credentials = defaultCredentials

        val initialUiSate = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )

        val firstNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName
            )
        )

        val lastNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName
            )
        )

        val emailEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email
            )
        )

        val passEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password
            )
        )

        val confirmPasswordEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
                confirmPassword = credentials.confirmPassword
            )
        )

        val viewStates = listOf(
            initialUiSate,
            firstNameEntered,
            lastNameEntered,
            emailEntered,
            passEntered,
            confirmPasswordEntered
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
                viewStates = viewStates,
            )
    }

    @Test
    fun `clicking register button when input fields are blank returns error for each field`() = runBlockingTest {
        val initialUiSate = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )

        val submittingState = RegisterUiState(
            isLoading = true,
            credentials = RegisterCredentials.EMPTY
        )

        val invalidInputState = RegisterUiState(
            credentials = RegisterCredentials.EMPTY,
            firstNameErrorMessage = UIText.ResourceText(R.string.err_empty_first_name),
            lastNameErrorMessage = UIText.ResourceText(R.string.err_empty_last_name),
            emailErrorMessage = UIText.ResourceText(R.string.err_empty_email),
            passwordErrorMessage = UIText.ResourceText(R.string.err_empty_password),
            confirmPasswordErrorMessage = UIText.ResourceText(R.string.err_empty_confirm_password)
        )

        val viewStates = listOf(
            initialUiSate,
            submittingState,
            invalidInputState
        )

        testRobot
            .buildViewModel()
            .mockRegisterResult(
                credentials = RegisterCredentials.EMPTY,
                result = RegisterResult.Failure.EmptyCredentials(
                    emptyFirstName = true,
                    emptyLastName = true,
                    emptyEmail = true,
                    emptyPassword = true,
                    emptyConfirmPassword = true
                )
            )
            .expectViewStates(
                action = {
                    clickRegisterButton()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `show a password mismatch error message when both passwords do not match`() = runBlockingTest {
        val credentials = defaultCredentials

        val initialUiSate = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )

        val firstNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName
            )
        )

        val lastNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName
            )
        )

        val emailEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email
            )
        )

        val passEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password
            )
        )

        val confirmPasswordEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
                confirmPassword = credentials.confirmPassword
            )
        )

        val submittingState = RegisterUiState(
            isLoading = true,
            credentials = credentials
        )

        val invalidInputState = RegisterUiState(
            credentials = credentials,
            errorMessage = UIText.ResourceText(R.string.err_mismatched_passwords)
        )

        val viewStates = listOf(
            initialUiSate,
            firstNameEntered,
            lastNameEntered,
            emailEntered,
            passEntered,
            confirmPasswordEntered,
            submittingState,
            invalidInputState
        )

        testRobot
            .buildViewModel()
            .mockRegisterResult(
                credentials = credentials,
                result = RegisterResult.Failure.MismatchedPassword
            )
            .expectViewStates(
                action = {
                    enterFirstName(credentials.firstName)
                    enterLastName(credentials.lastName)
                    enterEmail(credentials.email)
                    enterPassword(credentials.password)
                    enterConfirmationPassword(credentials.confirmPassword)
                    clickRegisterButton()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should return success result when registration is successful`() = runBlockingTest {
        val credentials = defaultCredentials

        val initialUiSate = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )

        val firstNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName
            )
        )

        val lastNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName
            )
        )

        val emailEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email
            )
        )

        val passEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password
            )
        )

        val confirmPasswordEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
                confirmPassword = credentials.confirmPassword
            )
        )
        val submittingState = RegisterUiState(
            isLoading = true,
            credentials = credentials
        )

        val successfulRegistrationState = RegisterUiState(
            credentials = credentials,
            registrationCompleted = true,
            isLoading = false
        )

        val navigationAfterSuccess = RegisterUiState(
            credentials = RegisterCredentials.EMPTY,
            registrationCompleted = true,
            isLoading = false
        )

        val viewStates = listOf(
            initialUiSate,
            firstNameEntered,
            lastNameEntered,
            emailEntered,
            passEntered,
            confirmPasswordEntered,
            submittingState,
            successfulRegistrationState,
            navigationAfterSuccess
        )

        testRobot
            .buildViewModel()
            .mockRegisterResult(
                credentials = credentials,
                result = RegisterResult.Success
            )
            .expectViewStates(
                action = {
                    enterFirstName(credentials.firstName)
                    enterLastName(credentials.lastName)
                    enterEmail(credentials.email)
                    enterPassword(credentials.password)
                    enterConfirmationPassword(credentials.confirmPassword)
                    clickRegisterButton()
                    onNavigate()
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun `should return error result when registration fails`() = runBlockingTest {
        val credentials = defaultCredentials

        val initialUiSate = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )

        val firstNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName
            )
        )

        val lastNameEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName
            )
        )

        val emailEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email
            )
        )

        val passEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password
            )
        )

        val confirmPasswordEntered = RegisterUiState(
            credentials = RegisterCredentials(
                firstName = credentials.firstName,
                lastName = credentials.lastName,
                email = credentials.email,
                password = credentials.password,
                confirmPassword = credentials.confirmPassword
            )
        )
        val submittingState = RegisterUiState(
            isLoading = true,
            credentials = credentials
        )

        val registrationErrorState = RegisterUiState(
            credentials = credentials,
            registrationCompleted = false,
            isLoading = false,
            errorMessage = UIText.ResourceText(R.string.err_unknown)
        )

        val viewStates = listOf(
            initialUiSate,
            firstNameEntered,
            lastNameEntered,
            emailEntered,
            passEntered,
            confirmPasswordEntered,
            submittingState,
            registrationErrorState

        )

        testRobot
            .buildViewModel()
            .mockRegisterResult(
                credentials = credentials,
                result = RegisterResult.Failure.Error()
            )
            .expectViewStates(
                action = {
                    enterFirstName(credentials.firstName)
                    enterLastName(credentials.lastName)
                    enterEmail(credentials.email)
                    enterPassword(credentials.password)
                    enterConfirmationPassword(credentials.confirmPassword)
                    clickRegisterButton()
                },
                viewStates = viewStates,
            )
    }
}
