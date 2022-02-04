package com.olabode.wilson.cyclee.core.domain.models

import androidx.annotation.StringRes

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

/**
 * Hold messages to be displayed to the UI.
 */
data class Message(
    val id: Long,
    @StringRes val messageId: Int
)
