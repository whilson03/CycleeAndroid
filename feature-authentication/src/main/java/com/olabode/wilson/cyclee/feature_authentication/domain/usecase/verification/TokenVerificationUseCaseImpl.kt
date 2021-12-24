package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

class TokenVerificationUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : TokenVerificationUseCase {

    override suspend fun invoke(token: VerificationToken): Result<String> {
        val validationResult = validateToken(token)

        if (validationResult != null) {
            return validationResult
        }

        return when (val result = authenticationRepository.verifyToken(token)) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> {
                Result.Error(message = result.message)
            }
        }
    }

    private fun validateToken(token: VerificationToken): Result.Error? {
        return when {
            token.token.isEmpty() -> Result.Error(message = AuthConstants.ERR_EMPTY_TOKEN)
            token.token.length != AuthConstants.TOKEN_LENGTH ->
                Result.Error(message = AuthConstants.ERR_INVALID_TOKEN)
            else -> null
        }
    }
}
