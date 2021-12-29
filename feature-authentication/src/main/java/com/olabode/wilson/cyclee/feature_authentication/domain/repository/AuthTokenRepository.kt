package com.olabode.wilson.cyclee.feature_authentication.domain.repository

import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.AuthToken

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

/**
 * This repository is responsible for fetching and storing a user's authentication token.
 */
interface AuthTokenRepository {

    /**
     * Given an [token], store this somewhere so it can be retrieved later.
     */
    suspend fun storeToken(
        token: AuthToken,
    )

    /**
     * Fetches the token of the signed is user, if we have one saved.
     *
     * @return The token or null if not found.
     */
    suspend fun fetchToken(): AuthToken?
}
