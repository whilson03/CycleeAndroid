package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

data class VerificationScreenUiState(
    val token: VerificationToken = VerificationToken(""),
    val email: String = "",
    val isRetryAvailable: Boolean = false,
    val isSendButtonEnabled: Boolean = false
)
