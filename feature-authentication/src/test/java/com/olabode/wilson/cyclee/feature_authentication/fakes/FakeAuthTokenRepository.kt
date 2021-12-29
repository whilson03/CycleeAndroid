package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.AuthToken
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthTokenRepository
import io.mockk.coVerify
import io.mockk.mockk

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeAuthTokenRepository {

    val mock: AuthTokenRepository = mockk(
        relaxUnitFun = true,
    )

    fun verifyTokenStored(token: AuthToken) {
        coVerify {
            mock.storeToken(token)
        }
    }

    fun verifyNoTokenStored() {
        coVerify(exactly = 0) {
            mock.storeToken(any())
        }
    }
}
