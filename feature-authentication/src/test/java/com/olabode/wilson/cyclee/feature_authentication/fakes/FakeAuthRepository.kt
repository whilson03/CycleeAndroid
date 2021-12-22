package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import io.mockk.coEvery
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 17/11/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeAuthRepository {
    val mock: AuthenticationRepository = mockk()

    fun mockRegistration(
        credentials: RegisterCredentials,
        result: Result<RegisterResponse>
    ) {
        coEvery {
            mock.register(credentials)
        } returns result
    }
}
