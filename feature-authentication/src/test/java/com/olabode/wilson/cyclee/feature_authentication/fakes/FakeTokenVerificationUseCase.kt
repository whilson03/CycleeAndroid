package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import io.mockk.coEvery
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeTokenVerificationUseCase {

    val mock: TokenVerificationUseCase = mockk()

    fun mockTokenVerificationResult(
        token: VerificationToken,
        result: Result<String>
    ) {
        coEvery {
            mock(token)
        } returns result
    }
}
