package com.olabode.wilson.cyclee.feature_authentication.domain.model

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

data class RegisterResponse(
    val id: String,
    val email: String,
    val token: String
)
