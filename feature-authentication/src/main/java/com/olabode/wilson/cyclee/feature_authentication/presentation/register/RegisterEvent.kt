package com.olabode.wilson.cyclee.feature_authentication.presentation.register

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/11/2021
 * EMAIL: whilson03@gmail.com
 */

internal sealed class RegisterEvent {
    data class EnteredFirstName(val value: String) : RegisterEvent()
    data class EnteredLastName(val value: String) : RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    data class EnteredConfirmationPassword(val value: String) : RegisterEvent()
    object TogglePasswordVisibility : RegisterEvent()
    object ToggleConfirmationPasswordVisibility : RegisterEvent()
    object Register : RegisterEvent()
}
