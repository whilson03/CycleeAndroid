package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login

import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginResult

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

interface LoginUseCase {

    suspend operator fun invoke(
        credential: LoginCredential,
    ): LoginResult
}
