package com.olabode.wilson.cyclee.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange600
        ),
        onClick = onClick
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Composable
@Preview
fun PreviewAuthButton() {
    AuthButton(text = "Auth Button") {
    }
}

@Immutable
data class TextInputFieldStyle(
    val imeAction: ImeAction = ImeAction.Default,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val keyboardAction: KeyboardActions = KeyboardActions(),
)

@ExperimentalComposeUiApi
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    style: TextInputFieldStyle = TextInputFieldStyle(),
    onValueChange: (value: String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = style.imeAction,
            keyboardType = style.keyboardType
        ),
        keyboardActions = style.keyboardAction
    )
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewTextInput() {
    TextInputField(
        value = "",
        label = "Hello",
    ) {
    }
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardAction: KeyboardActions = KeyboardActions(),
    onValueChange: (value: String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        keyboardActions = keyboardAction
    )
}

@Preview
@Composable
fun PreviewPasswordTextField() {
    PasswordTextField(
        value = "",
        label = "Password",
        keyboardAction = KeyboardActions()
    ) {
    }
}
