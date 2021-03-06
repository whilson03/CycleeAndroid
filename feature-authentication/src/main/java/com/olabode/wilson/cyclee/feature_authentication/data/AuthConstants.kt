package com.olabode.wilson.cyclee.feature_authentication.data

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

internal object AuthConstants {
    const val ERR_EMPTY_TOKEN = "Token can't be blank."
    const val ERR_INVALID_TOKEN = "Token is invalid."

    const val ERR_EMAIL_NOT_PROVIDED = "Email is required."

    const val TOKEN_LENGTH = 5

    const val TWO_MINUTES_IN_MILLIS = (2 * 60 * 1000).toLong()
}
