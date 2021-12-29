package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.domain.model.login.LoginCredential

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

data class LoginUiState(
    val credential: LoginCredential = LoginCredential("", ""),
    val emailErrorMessage: UIText? = null,
    val passwordErrorMessage: UIText? = null,
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val isUnauthenticatedUser: Boolean = false,
    val errorMessage: UIText? = null
)
