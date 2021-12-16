package com.olabode.wilson.cyclee.feature_authentication.data

import com.olabode.wilson.cyclee.core.data.dto.BasicApiResponse
import com.olabode.wilson.cyclee.feature_authentication.data.network.request.CreateAccountRequest
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 16/11/2021
 * EMAIL: whilson03@gmail.com
 */

interface AuthApiService {

    @POST("/api/auth/register")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<RegisterResponse>
}
