package com.olabode.wilson.cyclee.feature_authentication.data.repository

import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.AuthToken
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthTokenRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class AuthTokenRepositoryImpl @Inject constructor() : AuthTokenRepository {
    override suspend fun storeToken(token: AuthToken) {
        /* no-op */
    }

    override suspend fun fetchToken(): AuthToken? {
        return null
    }
}
