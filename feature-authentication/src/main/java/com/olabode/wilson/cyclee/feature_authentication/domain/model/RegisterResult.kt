package com.olabode.wilson.cyclee.feature_authentication.domain.model

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

sealed class RegisterResult {

    object Success : RegisterResult()

    sealed class Failure : RegisterResult() {

        object MismatchedPassword : Failure()

        data class Error(val errorMessage: String? = null) : Failure()

        data class EmptyCredentials(
            val emptyFirstName: Boolean,
            val emptyLastName: Boolean,
            val emptyEmail: Boolean,
            val emptyPassword: Boolean,
            val emptyConfirmPassword: Boolean
        ) : Failure()
    }
}
