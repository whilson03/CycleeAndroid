package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification

import com.olabode.wilson.cyclee.core.data.Result
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
        return when (val result = authenticationRepository.verifyToken(token)) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> {
                Result.Error(message = result.message)
            }
        }
    }
}
