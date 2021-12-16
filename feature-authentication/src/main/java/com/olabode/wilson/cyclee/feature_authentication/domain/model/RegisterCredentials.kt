package com.olabode.wilson.cyclee.feature_authentication.domain.model

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

data class RegisterCredentials(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
    companion object {
        val EMPTY = RegisterCredentials(
            firstName = "",
            lastName = "",
            email = "",
            password = "",
            confirmPassword = ""
        )
    }
}
