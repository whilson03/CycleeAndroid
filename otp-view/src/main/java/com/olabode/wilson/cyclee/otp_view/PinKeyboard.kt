package com.olabode.wilson.cyclee.otp_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
internal fun PinKeyboard(
    modifier: Modifier = Modifier,
    onClick: (value: Int) -> Unit,
    onDeleteClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val columnModifier = Modifier.weight(1f)

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "1", onClick = onClick)
                PinNumberButton(text = "4", onClick = onClick)
                PinNumberButton(text = "7", onClick = onClick)
            }

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "2", onClick = onClick)
                PinNumberButton(text = "5", onClick = onClick)
                PinNumberButton(text = "8", onClick = onClick)
                PinNumberButton(text = "0", onClick = onClick)
            }

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "3", onClick = onClick)
                PinNumberButton(text = "6", onClick = onClick)
                PinNumberButton(text = "9", onClick = onClick)
            }
        }

        PinDeleteButton(onClick = onDeleteClicked)
    }
}

@Preview
@Composable
fun PreviewPinKeyboard() {
    PinKeyboard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        onDeleteClicked = {}
    )
}
