package com.olabode.wilson.cyclee.otp_view

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
internal fun PinDeleteButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.delete),
    textSize: TextUnit = 15.sp,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}
