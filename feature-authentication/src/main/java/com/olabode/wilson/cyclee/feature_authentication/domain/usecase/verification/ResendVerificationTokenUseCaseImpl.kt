package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 28/12/2021
 * EMAIL: whilson03@gmail.com
 */

class ResendVerificationTokenUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ResendVerificationTokenUseCase {

    override suspend fun invoke(email: String): Result<String> {
        if (email.isEmpty()) {
            return Result.Error(message = AuthConstants.ERR_EMAIL_NOT_PROVIDED)
        }
        return when (val result = authenticationRepository.resendVerificationToken(email)) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> {
                Result.Error(message = result.message)
            }
        }
    }
}
