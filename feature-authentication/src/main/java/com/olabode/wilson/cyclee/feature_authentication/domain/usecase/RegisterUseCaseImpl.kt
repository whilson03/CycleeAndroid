package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

class RegisterUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : RegisterUseCase {

    override suspend fun invoke(credentials: RegisterCredentials): RegisterResult {
        val validationResult = validateCredentials(credentials)

        if (validationResult != null) {
            return validationResult
        }

        return when (val repoResult = authenticationRepository.register(credentials)) {
            is Result.Success -> {
                RegisterResult.Success
            }
            is Result.Error -> {
                RegisterResult.Failure.Error(repoResult.message)
            }
        }
    }

    private fun validateCredentials(credentials: RegisterCredentials): RegisterResult.Failure? {
        val emptyEmail = credentials.email.isEmpty()
        val emptyPassword = credentials.password.isEmpty()
        val emptyFirstName = credentials.firstName.isEmpty()
        val emptyLastName = credentials.lastName.isEmpty()
        val emptyConfirmPassword = credentials.confirmPassword.isEmpty()
        val failedMatchPassword = credentials.password != credentials.confirmPassword

        return when {
            emptyEmail || emptyPassword || emptyFirstName || emptyLastName ||
                emptyConfirmPassword -> {

                RegisterResult.Failure.EmptyCredentials(
                    emptyEmail = emptyEmail,
                    emptyPassword = emptyPassword,
                    emptyFirstName = emptyFirstName,
                    emptyLastName = emptyLastName,
                    failedPasswordMatch = failedMatchPassword,
                    emptyConfirmPassword = emptyConfirmPassword
                )
            }

            failedMatchPassword -> RegisterResult.Failure.MismatchedPassword
            else -> null
        }
    }
}
