package com.olabode.wilson.cyclee.feature_authentication.domain.model.login

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

/**
 * Contains the information necessary for authenticating network requests.
 *
 * @property[authToken] The current token used to validate a user's request.
 * @property[refreshToken] A token used to generate a new [authToken] if the current one is expired.
 */
data class AuthToken(
    val authToken: String,
    val refreshToken: String = "",
)
