package com.olabode.wilson.cyclee.feature_authentication.data.network.request

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 16/11/2021
 * EMAIL: whilson03@gmail.com
 */

data class CreateAccountRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
)
