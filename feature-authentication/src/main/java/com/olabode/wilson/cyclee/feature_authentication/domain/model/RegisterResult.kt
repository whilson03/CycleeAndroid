package com.olabode.wilson.cyclee.feature_authentication.domain.model

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

sealed class RegisterResult {

    object Success : RegisterResult()

    sealed class Failure : RegisterResult() {

        data class InvalidCredentials(val message: String = "") : Failure()

        object Unknown : Failure()

        data class EmptyCredentials(
            val firstName: Boolean,
            val lastName: Boolean,
            val emptyEmail: Boolean,
            val emptyPassword: Boolean,
        ) : Failure()
    }
}
