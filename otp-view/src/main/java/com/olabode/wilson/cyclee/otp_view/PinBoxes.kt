package com.olabode.wilson.cyclee.otp_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
internal fun BuildPinBoxes(
    noOfFields: Int,
    focusRequester: List<FocusRequester>,
    verificationCode: String
) {
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
        for (i in 0 until noOfFields) {
            BuildTextField(
                modifier = Modifier.focusRequester(focusRequester[i]),
                onValueChanged = {},
                text = if (verificationCode.length < i + 1) "" else verificationCode[i].toString()
            )
        }
    }
}
