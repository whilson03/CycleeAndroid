package com.olabode.wilson.cyclee.feature_authentication.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeader
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeaderImage
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthQuestionButton

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@ExperimentalComposeUiApi
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onSubmitForm: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AuthHeaderImage(headerImage = R.drawable.cyclist_riding_auth)
            AuthHeader(
                header = stringResource(id = R.string.login),
                subtitle = stringResource(R.string.login_account_message)
            )

            LoginFormContainer(
                uiState = uiState,
                onEmailChanged = onEmailChanged,
                onForgotPasswordClicked = onForgotPasswordClicked,
                onPasswordChanged = onPasswordChanged,
                onSubmitForm = onSubmitForm
            )
        }

        Box(
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        ) {
            AuthQuestionButton(
                question = stringResource(R.string.create_new_account_question),
                actionText = stringResource(R.string.create_new_account_question_action),
                actionTextColor = Orange600,
                onClick = onNavigateToRegister
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun PreviewLoginScreenContent() {
    LoginScreenContent(
        uiState = LoginUiState(),
        onEmailChanged = {},
        onPasswordChanged = {},
        onSubmitForm = {},
        onForgotPasswordClicked = {},
        onNavigateToRegister = {}
    )
}
