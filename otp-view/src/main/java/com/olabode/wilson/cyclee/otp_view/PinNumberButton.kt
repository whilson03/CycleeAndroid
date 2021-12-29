package com.olabode.wilson.cyclee.otp_view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
internal fun PinNumberButton(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 30.sp,
    textColor: Color = Color.Black,
    onClick: (value: Int) -> Unit,
) {
    TextButton(
        modifier = modifier.padding(4.dp),
        onClick = { onClick(text.toInt()) }
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

@Preview
@Composable
fun PreviewPinNumberButton() {
    PinNumberButton(
        modifier = Modifier,
        text = "6"
    ) {}
}
