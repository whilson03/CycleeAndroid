package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthButton
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.PasswordTextField
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.TextInputField
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.TextInputFieldStyle

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 19/09/2021
 * EMAIL: whilson03@gmail.com
 */

@ExperimentalComposeUiApi
@Composable
fun RegisterFormContainer(
    modifier: Modifier = Modifier,
    onNameChanged: (name: String) -> Unit,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onConfirmationPasswordChanged: (password: String) -> Unit,
    onSubmitForm: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.full_name),
            onValueChange = onNameChanged
        )

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.email),
            style = TextInputFieldStyle(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = onEmailChanged
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.password),
            onValueChange = onPasswordChanged
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = stringResource(R.string.confirm_password),
            onValueChange = onConfirmationPasswordChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(R.string.sign_up),
            onClick = onSubmitForm
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewRegisterFormContainer() {
    RegisterFormContainer(
        onEmailChanged = {},
        onPasswordChanged = {},
        onSubmitForm = {},
        onConfirmationPasswordChanged = {},
        onNameChanged = {}
    )
}
