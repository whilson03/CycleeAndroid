package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import com.olabode.wilson.cyclee.common_ui.ui.UIText
import com.olabode.wilson.cyclee.feature_authentication.domain.model.RegisterCredentials

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 16/11/2021q
 * EMAIL: whilson03@gmail.com
 */

/**
 * A sealed class defining all possible states of our register screen.
 *
 * @property[credentials] The current RegisterCredentials entered by the user.
 * @property[inputsEnabled] If true, the buttons on the register screen can accept clicks,
 * false otherwise.
 */
sealed class RegisterViewState(
    open val credentials: RegisterCredentials,
    open val inputsEnabled: Boolean = true,
) {
    /**
     * The initial state of the screen with nothing input.
     */
    object Initial : RegisterViewState(
        credentials = RegisterCredentials.EMPTY,
    )

    /**
     * The state of the screen as the user is entering email information.
     */
    data class Active(
        override val credentials: RegisterCredentials,
        val firstNameInputErrorMessage: UIText? = null,
        val lastNameInputErrorMessage: UIText? = null,
        val emailInputErrorMessage: UIText? = null,
        val passwordInputErrorMessage: UIText? = null,
        val confirmPasswordInputErrorMessage: UIText? = null,
    ) : RegisterViewState(
        credentials = credentials,
    )

    /**
     * The state of the screen as the user is attempting to login.
     */
    data class Submitting(
        override val credentials: RegisterCredentials,
    ) : RegisterViewState(
        credentials = credentials,
        inputsEnabled = false,
    )

    /**
     * The state of the screen when there is an error logging in.
     */
    data class SubmissionError(
        override val credentials: RegisterCredentials,
        val errorMessage: UIText,
    ) : RegisterViewState(
        credentials = credentials,
    )

    object Completed : RegisterViewState(
        credentials = RegisterCredentials.EMPTY,
        inputsEnabled = false,
    )
}
