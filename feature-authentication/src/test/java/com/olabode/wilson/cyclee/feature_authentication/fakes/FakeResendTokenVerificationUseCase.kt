package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.ResendVerificationTokenUseCase
import io.mockk.coEvery
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 28/12/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeResendTokenVerificationUseCase {

    val mock: ResendVerificationTokenUseCase = mockk()

    fun mockResendTokenVerificationResult(
        email: String,
        result: Result<String>
    ) {
        coEvery {
            mock(email)
        } returns result
    }
}
