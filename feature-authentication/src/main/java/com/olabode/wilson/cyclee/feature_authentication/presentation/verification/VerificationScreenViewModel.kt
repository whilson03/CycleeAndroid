package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import android.util.Log
import androidx.lifecycle.ViewModel
import com.olabode.wilson.cyclee.feature_authentication.data.AuthConstants
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@HiltViewModel
class VerificationScreenViewModel @Inject constructor(
    private val tokenVerificationUseCase: TokenVerificationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<VerificationScreenUiState> =
        MutableStateFlow(VerificationScreenUiState())
    val uiState: StateFlow<VerificationScreenUiState> = _uiState

    fun onTokenChanged(token: String) {
        val newToken = uiState.value.token.copy(token = token)
        _uiState.value = _uiState.value.copy(
            token = newToken,
            isSendButtonEnabled = newToken.token.length == AuthConstants.TOKEN_LENGTH
        )
        Log.e("NEW STATE", uiState.value.toString())
    }

    fun onResendToken() {
        // todo
    }

    fun submitToken() {
        // todo
    }
}
