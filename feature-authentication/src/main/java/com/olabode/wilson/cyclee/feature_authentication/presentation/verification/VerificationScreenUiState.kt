package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationCredentials

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

data class VerificationScreenUiState(
    val credentials: VerificationCredentials = VerificationCredentials(),
    val email: UIText? = null,
    val errorMessage: UIText? = null,
    val isLoading: Boolean = false,
    val isResendButtonEnabled: Boolean = false,
    val isSendButtonEnabled: Boolean = false
)
