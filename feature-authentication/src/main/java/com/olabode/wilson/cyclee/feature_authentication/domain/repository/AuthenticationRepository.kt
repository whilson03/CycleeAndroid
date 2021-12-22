package com.olabode.wilson.cyclee.feature_authentication.domain.repository

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

interface AuthenticationRepository {

    suspend fun register(
        credentials: RegisterCredentials
    ): Result<RegisterResponse>
}
