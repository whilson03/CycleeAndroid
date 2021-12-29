package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.LoginResponse
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials
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

    fun mockTokenVerification(
        credentials: VerificationCredentials,
        result: Result<String>
    ) {
        coEvery {
            mock.verifyToken(credentials)
        } returns result
    }

    fun mockLogin(
        credential: LoginCredential,
        result: Result<LoginResponse>
    ) {
        coEvery {
            mock.login(credential)
        } returns result
    }
}
