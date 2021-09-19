package com.olabode.wilson.cyclee.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.R
import com.olabode.wilson.cyclee.ui.component.AuthButton
import com.olabode.wilson.cyclee.ui.component.PasswordTextField
import com.olabode.wilson.cyclee.ui.component.TextInputField
import com.olabode.wilson.cyclee.ui.component.TextInputFieldStyle
import com.olabode.wilson.cyclee.ui.theme.Orange600

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 19/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
fun ForgotPasswordButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.forgot_password),
    onClick: () -> Unit
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(
            text = text,
            color = Orange600
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun LoginFormContainer(
    modifier: Modifier = Modifier,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onSubmitForm: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.email),
            style = TextInputFieldStyle(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = onEmailChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.password),
            onValueChange = onPasswordChanged
        )
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPasswordButton(onClick = onForgotPasswordClicked)
        Spacer(modifier = Modifier.height(20.dp))
        AuthButton(
            text = stringResource(R.string.login),
            onClick = onSubmitForm
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLoginLoginFormContainer() {
    LoginFormContainer(
        onEmailChanged = {},
        onForgotPasswordClicked = {},
        onPasswordChanged = {},
        onSubmitForm = {}
    )
}
