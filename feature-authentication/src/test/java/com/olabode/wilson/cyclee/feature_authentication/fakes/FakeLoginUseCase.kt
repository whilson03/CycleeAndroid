package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeLoginUseCase {

    val mock: LoginUseCase = mockk()

    fun mockLoginResult(
        credential: LoginCredential,
        result: LoginResult
    ) {
        coEvery {
            mock(credential)
        } returns result
    }
}
