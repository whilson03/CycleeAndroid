package com.olabode.wilson.cyclee.core.utils.snackbar

import com.olabode.wilson.cyclee.core.domain.models.Message
import kotlinx.coroutines.flow.StateFlow

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

interface SnackbarManager {

    val messages: StateFlow<List<Message>>

    fun showMessage(messageId: Int)

    fun setMessageShown(messageId: Long)
}
