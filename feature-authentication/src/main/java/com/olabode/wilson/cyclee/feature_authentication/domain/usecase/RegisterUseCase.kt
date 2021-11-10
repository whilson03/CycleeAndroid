package com.olabode.wilson.cyclee.feature_authentication.domain.usecase

import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterResult

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

interface RegisterUseCase {

    suspend operator fun invoke(
        credentials: RegisterCredentials,
    ): RegisterResult
}
