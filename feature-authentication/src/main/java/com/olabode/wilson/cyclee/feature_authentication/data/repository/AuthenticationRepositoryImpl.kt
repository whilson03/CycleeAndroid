package com.olabode.wilson.cyclee.feature_authentication.data.repository

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthApiService
import com.olabode.wilson.cyclee.feature_authentication.data.network.request.CreateAccountRequest
import com.olabode.wilson.cyclee.feature_authentication.data.network.response.RegisterResponse
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import com.olabode.wilson.cyclee.networking.constants.NetworkConstants
import com.olabode.wilson.cyclee.networking.domain.models.NetworkResult
import com.olabode.wilson.cyclee.networking.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

class AuthenticationRepositoryImpl @Inject constructor(
    private val authApi: AuthApiService
) : AuthenticationRepository {

    override suspend fun register(credentials: RegisterCredentials): Result<RegisterResponse> {
        val request = CreateAccountRequest(
            firstname = credentials.firstName,
            lastname = credentials.lastName,
            email = credentials.email,
            password = credentials.password
        )
        val result = safeApiCall(Dispatchers.IO) { authApi.register(request) }

        return when (result) {
            is NetworkResult.NetworkError -> Result.Error()
            is NetworkResult.GenericError -> Result.Error(message = result.error?.errorMessage)
            is NetworkResult.Success -> Result.Success(result.value)
        }
    }

    override suspend fun verify(token: VerificationToken): Result<String> {
        val result = safeApiCall(Dispatchers.IO) { authApi.verifyToken(token.token) }

        return when (result) {
            is NetworkResult.NetworkError -> Result.Error()
            is NetworkResult.GenericError -> Result.Error(message = result.error?.errorMessage)
            is NetworkResult.Success -> Result.Success(
                result.value.message ?: NetworkConstants.GENERIC_SUCCESS_MESSAGE
            )
        }
    }
}
