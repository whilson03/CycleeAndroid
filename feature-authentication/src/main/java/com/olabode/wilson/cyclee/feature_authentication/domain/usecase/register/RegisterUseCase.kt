package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.register

import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterCredentials
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterResult

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
