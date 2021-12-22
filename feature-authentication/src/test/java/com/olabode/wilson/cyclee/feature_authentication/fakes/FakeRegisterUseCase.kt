package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.RegisterUseCase
import io.mockk.coEvery
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/11/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeRegisterUseCase {

    val mock: RegisterUseCase = mockk()

    fun mockRegisterResult(
        credentials: RegisterCredentials,
        result: RegisterResult
    ) {
        coEvery {
            mock(credentials)
        } returns result
    }
}
