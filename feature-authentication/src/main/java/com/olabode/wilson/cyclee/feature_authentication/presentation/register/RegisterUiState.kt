package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 20/12/2021
 * EMAIL: whilson03@gmail.com
 */

data class RegisterUiState(
    val isLoading: Boolean = false,
    val registrationCompleted: Boolean = false,
    val credentials: RegisterCredentials,
    val firstNameErrorMessage: UIText? = null,
    val lastNameErrorMessage: UIText? = null,
    val emailErrorMessage: UIText? = null,
    val passwordErrorMessage: UIText? = null,
    val confirmPasswordErrorMessage: UIText? = null,
    val errorMessage: UIText? = null
)
