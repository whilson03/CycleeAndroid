package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.InvalidCredentialsException
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

    private val defaultCredentials = RegisterCredentials(
        firstName = "aaaaa",
        lastName = "aaaaa",
        email = "a@mail.com",
        password = "a",
        confirmPassword = "a"
    )

    private lateinit var authRepository: FakeAuthRepository

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSuccessfulRegistration() = runBlockingTest {

        val mockResponse = RegisterResponse(
            id = "1",
            "a@mail.com",
            "11111"
        )

        val mockResult = Result.Success(mockResponse)

        authRepository.apply {
            mockRegistration(
                defaultCredentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(defaultCredentials)

        assertThat(result).isEqualTo(RegisterResult.Success)
    }

    @Test
    fun testUnknownFailureRegistration() = runBlockingTest {

        val mockResult: Result<RegisterResponse> = Result.Error(
            Throwable("something went wrong"), ""
        )

        authRepository.apply {
            mockRegistration(
                defaultCredentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(defaultCredentials)

        assertThat(result).isEqualTo(RegisterResult.Failure.Unknown)
    }

    @Test
    fun testInvalidCredentialsRegistration() = runBlockingTest {

        val mockResult: Result<RegisterResponse> = Result.Error(
            InvalidCredentialsException(), ""
        )

        authRepository.apply {
            mockRegistration(
                defaultCredentials,
                mockResult
            )
        }

        val usecase = RegisterUseCaseImpl(authRepository.mock)
        val result = usecase(defaultCredentials)

        assertThat(result).isEqualTo(RegisterResult.Failure.InvalidCredentials)
    }
}
