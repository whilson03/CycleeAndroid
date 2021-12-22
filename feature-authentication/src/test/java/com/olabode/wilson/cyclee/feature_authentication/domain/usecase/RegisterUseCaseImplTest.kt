package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 17/11/2021
 * EMAIL: whilson03@gmail.com
 */

class RegisterUseCaseImplTest {

    private lateinit var authRepository: FakeAuthRepository

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSuccessfulRegistration() = runBlockingTest {
        val credentials = RegisterCredentials(
            firstName = "aaaaa",
            lastName = "aaaaa",
            email = "a@mail.com",
            password = "a",
            confirmPassword = "a"
        )

        val mockResponse = RegisterResponse(
            id = "1",
            "a@mail.com"
        )

        val mockResult = Result.Success(mockResponse)

        authRepository.apply {
            mockRegistration(
                credentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(credentials)

        assertThat(result).isEqualTo(RegisterResult.Success)
    }

    @Test
    fun testEmptyCredentialFailureRegistration() = runBlockingTest {
        val credentials = RegisterCredentials.EMPTY

        val mockResult: Result<RegisterResponse> = Result.Error()

        authRepository.apply {
            mockRegistration(
                credentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(credentials)

        assertThat(result).isEqualTo(
            RegisterResult.Failure.EmptyCredentials(
                emptyFirstName = true,
                emptyLastName = true,
                emptyEmail = true,
                emptyPassword = true,
                emptyConfirmPassword = true
            )
        )
    }

    @Test
    fun testMismatchedPasswordsFailureRegistration() = runBlockingTest {
        val credentials = RegisterCredentials(
            firstName = "aaa",
            lastName = "aaa",
            email = "email",
            password = "aa",
            confirmPassword = "a"
        )
        val mockResult: Result<RegisterResponse> = Result.Error()

        authRepository.apply {
            mockRegistration(
                credentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(credentials)

        assertThat(result).isEqualTo(RegisterResult.Failure.MismatchedPassword)
    }
}
