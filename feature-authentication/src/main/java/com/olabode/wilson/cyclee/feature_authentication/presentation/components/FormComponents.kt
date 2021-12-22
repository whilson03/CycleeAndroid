package com.olabode.wilson.cyclee.feature_authentication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_authentication.R

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 05/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange600
        ),
        enabled = enabled,
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
    val imeAction: ImeAction = ImeAction.Next,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val keyboardAction: KeyboardActions = KeyboardActions(),
    val focusedIndicatorColor: Color = Color.Transparent,
    val unfocusedIndicatorColor: Color = Color.Transparent,
    val backgroundColor: Color = Color.LightGray
)

@ExperimentalComposeUiApi
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    errorMessage: String? = null,
    style: TextInputFieldStyle = TextInputFieldStyle(),
    onValueChange: (value: String) -> Unit
) {
    Column {
        TextField(
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            value = value,
            label = { Text(text = label) },
            onValueChange = onValueChange,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = style.imeAction,
                keyboardType = style.keyboardType
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = style.focusedIndicatorColor,
                unfocusedIndicatorColor = style.unfocusedIndicatorColor
            ),
            keyboardActions = style.keyboardAction,
            isError = (errorMessage != null),
        )

        errorMessage?.let { ErrorText(message = it) }
    }
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
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    keyboardAction: KeyboardActions = KeyboardActions(),
    onValueChange: (value: String) -> Unit
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    Column {
        TextField(
            modifier = modifier.clip(RoundedCornerShape(8.dp)),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            maxLines = 1,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            isError = (errorMessage != null),
            keyboardActions = keyboardAction,
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val icon = if (passwordHidden) {
                        R.drawable.ic_visibility_on
                    } else {
                        R.drawable.ic_visibility_off
                    }
                    Icon(painter = painterResource(id = icon), contentDescription = null)
                }
            }
        )

        errorMessage?.let { ErrorText(message = it) }
    }
}

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    message: String,
    color: Color = MaterialTheme.colors.error,
    style: TextStyle = MaterialTheme.typography.caption
) {
    Text(
        modifier = modifier.padding(top = 1.dp, start = 16.dp),
        text = message,
        color = color,
        style = style
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
