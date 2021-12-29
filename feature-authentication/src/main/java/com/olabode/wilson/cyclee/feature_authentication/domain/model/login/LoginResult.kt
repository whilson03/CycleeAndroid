package com.olabode.wilson.cyclee.feature_authentication.domain.model.login

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

sealed class LoginResult {

    object Success : LoginResult()

    sealed class Failure : LoginResult() {

        object InvalidCredentials : Failure()

        object UnVerifiedAccount : Failure()

        data class Error(val errorMessage: String? = null) : Failure()

        data class EmptyCredentials(
            val emptyEmail: Boolean,
            val emptyPassword: Boolean
        ) : Failure()
    }
}
