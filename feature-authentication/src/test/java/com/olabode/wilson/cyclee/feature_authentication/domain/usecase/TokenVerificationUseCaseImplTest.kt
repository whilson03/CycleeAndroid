package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
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

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
    }

    @Test
    fun testSuccessfulTokenVerification() = runBlockingTest {
        val token = VerificationToken("123456")

        val mockResult = Result.Success("")

        authRepository.apply {
            mockTokenVerification(
                token = token,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(token)

        assertThat(result).isEqualTo(Result.Success(""))
    }

    @Test
    fun testEmptyTokenReturnsError() = runBlockingTest {
        val token = VerificationToken("")

        val mockResult = Result.Error()

        authRepository.apply {
            mockTokenVerification(
                token = token,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(token)

        assertThat(result).isEqualTo(Result.Error(message = AuthConstants.ERR_EMPTY_TOKEN))
    }

    @Test
    fun testInvalidTokenLengthReturnsError() = runBlockingTest {
        val invalidToken = VerificationToken("1")

        val mockResult = Result.Error()

        authRepository.apply {
            mockTokenVerification(
                token = invalidToken,
                result = mockResult
            )
        }

        val usecase = TokenVerificationUseCaseImpl(authRepository.mock)
        val result = usecase(invalidToken)

        assertThat(result).isEqualTo(Result.Error(message = AuthConstants.ERR_INVALID_TOKEN))
    }
}
