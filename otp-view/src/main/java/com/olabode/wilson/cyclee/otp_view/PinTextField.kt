package com.olabode.wilson.cyclee.otp_view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
internal fun BuildTextField(
    modifier: Modifier = Modifier,
    text: String,
    style: PinViewStyle = PinViewStyle.DEFAULT,
    onValueChanged: (text: String) -> Unit,
) {
    OutlinedTextField(
        textStyle = style.textStyle,
        readOnly = true,
        modifier = modifier
            .width(style.width)
            .padding(2.dp),
        value = text,
        onValueChange = onValueChanged,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = style.focusedColor,
            backgroundColor = style.backgroundColor,
            unfocusedIndicatorColor = style.unfocusedColour

        ),
    )
}
