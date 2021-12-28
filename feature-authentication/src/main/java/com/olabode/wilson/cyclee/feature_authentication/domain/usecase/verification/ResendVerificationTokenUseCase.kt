package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification

import com.olabode.wilson.cyclee.core.data.Result

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 28/12/2021
 * EMAIL: whilson03@gmail.com
 */

interface ResendVerificationTokenUseCase {

    suspend operator fun invoke(
        email: String
    ): Result<String>
}
