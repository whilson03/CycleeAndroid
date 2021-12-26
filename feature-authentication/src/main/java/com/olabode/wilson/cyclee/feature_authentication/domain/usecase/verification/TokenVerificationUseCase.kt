package com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification

import com.olabode.wilson.cyclee.core.data.Result
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

interface TokenVerificationUseCase {

    suspend operator fun invoke(
        credentials: VerificationCredentials
    ): Result<String>
}
