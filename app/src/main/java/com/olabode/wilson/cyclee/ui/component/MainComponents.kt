package com.olabode.wilson.cyclee.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.ui.theme.Orange600

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 05/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 4.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 4.dp
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange600
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview
fun PreviewAuthButton() {
    AuthButton(text = "Auth Button") {
    }
}
