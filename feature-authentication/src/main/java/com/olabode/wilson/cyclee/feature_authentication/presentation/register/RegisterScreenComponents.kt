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
import com.olabode.wilson.cyclee.common_ui.ui.getString
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.domain.model.register.RegisterCredentials
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
    viewState: RegisterUiState,
    onFirstNameChanged: (firstName: String) -> Unit,
    onLastNameChanged: (lastName: String) -> Unit,
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
            value = viewState.credentials.firstName,
            label = stringResource(R.string.first_name),
            errorMessage = viewState.firstNameErrorMessage?.getString(),
            onValueChange = onFirstNameChanged
        )

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.credentials.lastName,
            label = stringResource(R.string.last_name),
            errorMessage = viewState.lastNameErrorMessage?.getString(),
            onValueChange = onLastNameChanged
        )

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.credentials.email,
            label = stringResource(R.string.email),
            style = TextInputFieldStyle(
                keyboardType = KeyboardType.Email
            ),
            errorMessage = viewState.emailErrorMessage?.getString(),
            onValueChange = onEmailChanged
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.credentials.password,
            label = stringResource(R.string.password),
            errorMessage = viewState.passwordErrorMessage?.getString(),
            onValueChange = onPasswordChanged
        )

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.credentials.confirmPassword,
            label = stringResource(R.string.confirm_password),
            errorMessage = viewState.confirmPasswordErrorMessage?.getString(),
            onValueChange = onConfirmationPasswordChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthButton(
            text = stringResource(R.string.sign_up),
            onClick = onSubmitForm,
            enabled = !viewState.isLoading
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
        onFirstNameChanged = {},
        onLastNameChanged = {},
        viewState = RegisterUiState(
            credentials = RegisterCredentials.EMPTY
        )
    )
}
