package com.olabode.wilson.cyclee.feature_authentication.data

import com.olabode.wilson.cyclee.feature_authentication.data.network.request.CreateAccountRequest
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.LoginResponse
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.networking.constants.NetworkConstants
import com.olabode.wilson.cyclee.networking.domain.models.BasicApiResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 16/11/2021
 * EMAIL: whilson03@gmail.com
 */

interface AuthApiService {

    @POST("$authPath/register")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): RegisterResponse

    @FormUrlEncoded
    @POST("$authPath/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("$authPath/verify")
    suspend fun verifyToken(
        @Field("token") token: String,
        @Field("email") email: String
    ): BasicApiResponse

    @FormUrlEncoded
    @POST("$authPath/resend-token")
    suspend fun resendVerificationToken(
        @Field("email") email: String
    ): BasicApiResponse

    companion object {
        private const val authPath = NetworkConstants.BASE_AUTH_URL
    }
}
