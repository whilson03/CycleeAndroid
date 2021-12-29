package com.olabode.wilson.cyclee.otp_view

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Immutable
class PinViewStyle(
    val width: Dp = 60.dp,
    val textStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    ),
    val focusedColor: Color = Color.Blue,
    val backgroundColor: Color = Color.Gray,
    val unfocusedColour: Color = Color.LightGray,
) {

    companion object {
        val DEFAULT = PinViewStyle()
    }
}
