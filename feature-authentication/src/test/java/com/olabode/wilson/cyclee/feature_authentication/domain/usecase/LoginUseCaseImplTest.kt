package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.LoginResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.AuthToken
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeAuthRepository
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeAuthTokenRepository
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
    private lateinit var tokenRepository: FakeAuthTokenRepository

    private val defaultToken = AuthToken(
        authToken = "Auth",
        refreshToken = "Refresh"
    )

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
        tokenRepository = FakeAuthTokenRepository()
    }

    @Test
    fun `should return success when correct login credentials are provided`() = runBlockingTest {
        val credentials = LoginCredential(email = "a@mail.com", password = "a")

        val mockResponse = LoginResponse(token = defaultToken)
        val mockResult: Result<LoginResponse> = Result.Success(mockResponse)

        authRepository.apply {
            mockLogin(credentials, mockResult)
        }

        val usecase = LoginUseCaseImpl(
            authenticationRepository = authRepository.mock,
            tokenRepository = tokenRepository.mock
        )
        val result = usecase(credentials)

        assertThat(result).isEqualTo(LoginResult.Success)
        tokenRepository.verifyTokenStored(defaultToken)
    }

    @Test
    fun `should return empty credential error if empty login credentials are provided`() =
        runBlockingTest {

            val credentials = LoginCredential(email = "", password = "")

            val mockResult: Result<LoginResponse> = Result.Error()

            authRepository.apply {
                mockLogin(credentials, mockResult)
            }

            val usecase = LoginUseCaseImpl(
                authenticationRepository = authRepository.mock,
                tokenRepository = tokenRepository.mock
            )
            val result = usecase(credentials)

            assertThat(result).isEqualTo(
                LoginResult.Failure.EmptyCredentials(emptyEmail = true, emptyPassword = true)
            )
            tokenRepository.verifyNoTokenStored()
        }

    @Test
    fun `should return unauthenticated error if error status code is 401`() = runBlockingTest {
        val credentials = LoginCredential(email = "a@mail.com", password = "password")

        val mockResult: Result<LoginResponse> = Result.Error(statusCode = 401)

        authRepository.apply {
            mockLogin(credentials, mockResult)
        }

        val usecase = LoginUseCaseImpl(
            authenticationRepository = authRepository.mock,
            tokenRepository = tokenRepository.mock
        )

        val result = usecase(credentials)

        assertThat(result).isEqualTo(LoginResult.Failure.UnVerifiedAccount)
        tokenRepository.verifyNoTokenStored()
    }
}
