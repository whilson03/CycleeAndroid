package com.olabode.wilson.cyclee.core.utils.snackbar

import com.olabode.wilson.cyclee.core.domain.models.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

class SnackbarManagerImpl @Inject constructor() : SnackbarManager {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())

    override val messages: StateFlow<List<Message>>
        get() = _messages

    override fun showMessage(messageId: Int) {
        _messages.update { currentMessages ->
            currentMessages + Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageId
            )
        }
    }

    override fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }
}
