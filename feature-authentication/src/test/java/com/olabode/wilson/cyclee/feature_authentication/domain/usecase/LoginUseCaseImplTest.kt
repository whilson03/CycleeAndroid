package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.LoginResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeAuthRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class LoginUseCaseImplTest {
    private lateinit var authRepository: FakeAuthRepository

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
    }

    @Test
    fun `should return success when correct login credentials are provided`() = runBlockingTest {
        val credentials = LoginCredential(email = "a@mail.com", password = "a")

        val mockResponse = LoginResponse()
        val mockResult: Result<LoginResponse> = Result.Success(mockResponse)

        authRepository.apply {
            mockLogin(credentials, mockResult)
        }

        val usecase = LoginUseCaseImpl(authRepository.mock)
        val result = usecase(credentials)

        assertThat(result).isEqualTo(LoginResult.Success)
    }

    @Test
    fun `should return empty credential error if empty login credentials are provided`() =
        runBlockingTest {

            val credentials = LoginCredential(email = "", password = "")

            val mockResult: Result<LoginResponse> = Result.Error()

            authRepository.apply {
                mockLogin(credentials, mockResult)
            }

            val usecase = LoginUseCaseImpl(authRepository.mock)
            val result = usecase(credentials)

            assertThat(result).isEqualTo(
                LoginResult.Failure.EmptyCredentials(emptyEmail = true, emptyPassword = true)
            )
        }

    @Test
    fun `should return unauthenticated error if error status code is 401`() = runBlockingTest {
        val credentials = LoginCredential(email = "a@mail.com", password = "password")

        val mockResult: Result<LoginResponse> = Result.Error(statusCode = 401)

        authRepository.apply {
            mockLogin(credentials, mockResult)
        }

        val usecase = LoginUseCaseImpl(authRepository.mock)
        val result = usecase(credentials)

        assertThat(result).isEqualTo(LoginResult.Failure.UnVerifiedAccount)
    }
}
