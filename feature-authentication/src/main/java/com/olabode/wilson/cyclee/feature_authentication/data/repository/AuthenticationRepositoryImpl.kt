package com.olabode.wilson.cyclee.feature_authentication.data.repository

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

class AuthenticationRepositoryImpl @Inject constructor() : AuthenticationRepository {

    override suspend fun register(credentials: RegisterCredentials): Result<RegisterResponse> {
        TODO("Not yet implemented")
    }
}
