package com.olabode.wilson.cyclee.feature_authentication.domain.model

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

data class RegisterCredentials(
    val firstName: FirstName = FirstName(""),
    val lastName: LastName = LastName(""),
    val email: Email = Email(""),
    val password: Password = Password(""),
)
