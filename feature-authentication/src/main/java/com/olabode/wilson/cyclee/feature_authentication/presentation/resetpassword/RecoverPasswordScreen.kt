package com.olabode.wilson.cyclee.feature_authentication.presentation.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeader
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeaderImage
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.TextInputField
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.TextInputFieldStyle

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
@Composable
fun RecoverPasswordScreen(
    modifier: Modifier = Modifier,
    onNavigateToVerification: () -> Unit
) {
    RecoverPasswordScreenContent(
        onSubmitForm = {},
        onEmailChanged = {}
    )
}

@ExperimentalComposeUiApi
@Composable
internal fun RecoverPasswordScreenContent(
    modifier: Modifier = Modifier,
    onEmailChanged: (email: String) -> Unit,
    onSubmitForm: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AuthHeaderImage(headerImage = R.drawable.cyclist_riding_auth)
            AuthHeader(
                header = stringResource(R.string.password_recovery_header),
                subtitle = stringResource(R.string.password_recovery_subtitle)
            )
            PasswordRecoveryFormContainer(
                modifier,
                onEmailChanged,
                onSubmitForm
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
@Preview
@Composable
private fun PreviewRecoverPasswordScreen() {
    RecoverPasswordScreenContent(
        onSubmitForm = {},
        onEmailChanged = {}
    )
}

@ExperimentalComposeUiApi
@Composable
internal fun PasswordRecoveryFormContainer(
    modifier: Modifier = Modifier,
    onEmailChanged: (email: String) -> Unit,
    onSubmitForm: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
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
        AuthButton(
            text = stringResource(R.string.submit),
            onClick = onSubmitForm
        )
    }
}
