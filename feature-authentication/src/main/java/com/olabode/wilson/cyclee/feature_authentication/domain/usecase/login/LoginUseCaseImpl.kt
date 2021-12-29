package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthTokenRepository
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

class LoginUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenRepository: AuthTokenRepository
) : LoginUseCase {

    override suspend fun invoke(credential: LoginCredential): LoginResult {
        val validationResult = validateCredentials(credential)

        if (validationResult != null) {
            return validationResult
        }

        return when (val repoResult = authenticationRepository.login(credential)) {
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                LoginResult.Success
            }
            is Result.Error -> {
                handleError(repoResult)
            }
        }
    }

    private fun handleError(result: Result.Error): LoginResult {
        return when (result.statusCode) {
            401 -> {
                LoginResult.Failure.UnVerifiedAccount
            }
            else -> LoginResult.Failure.Error(result.message)
        }
    }

    private fun validateCredentials(credential: LoginCredential): LoginResult.Failure? {
        val emptyEmail = credential.email.isEmpty()
        val emptyPassword = credential.password.isEmpty()

        return when {
            emptyEmail || emptyPassword -> {
                LoginResult.Failure.EmptyCredentials(
                    emptyEmail = emptyEmail,
                    emptyPassword = emptyPassword
                )
            }
            else -> null
        }
    }
}
