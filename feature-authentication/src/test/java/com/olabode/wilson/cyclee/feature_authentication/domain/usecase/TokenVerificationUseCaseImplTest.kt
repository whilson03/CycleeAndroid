package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.fakes.FakeAuthRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

class TokenVerificationUseCaseImplTest {

    private lateinit var authRepository: FakeAuthRepository
    private val testEmail = "test@mail.com"

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
    }

    @Test
    fun `should return success when verification is successful`() = runBlockingTest {
        val credential = VerificationCredentials(token = "12345", email = testEmail)

        val mockResult = Result.Success("")

        authRepository.apply {
            mockTokenVerification(
                credentials = credential,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(credentials = credential)

        assertThat(result).isEqualTo(Result.Success(""))
    }

    @Test
    fun `should return error when an empty token is submitted`() = runBlockingTest {
        val credential = VerificationCredentials(token = "", email = testEmail)

        val mockResult = Result.Error()

        authRepository.apply {
            mockTokenVerification(
                credentials = credential,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(credentials = credential)

        assertThat(result).isEqualTo(Result.Error(message = AuthConstants.ERR_EMPTY_TOKEN))
    }

    @Test
    fun `should return error when an invalid token is submitted`() = runBlockingTest {
        val credential = VerificationCredentials(token = "1", email = testEmail)

        val mockResult = Result.Error()

        authRepository.apply {
            mockTokenVerification(
                credentials = credential,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(credentials = credential)

        assertThat(result).isEqualTo(Result.Error(message = AuthConstants.ERR_INVALID_TOKEN))
    }

    @Test
    fun `should return error when an email is not provided for verification`() = runBlockingTest {
        val credential = VerificationCredentials(token = "12345", email = "")

        val mockResult = Result.Error()

        authRepository.apply {
            mockTokenVerification(
                credentials = credential,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(credentials = credential)

        assertThat(result).isEqualTo(Result.Error(message = AuthConstants.ERR_EMAIL_NOT_PROVIDED))
    }
}
