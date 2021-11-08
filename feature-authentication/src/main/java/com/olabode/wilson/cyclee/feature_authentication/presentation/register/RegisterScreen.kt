package com.olabode.wilson.cyclee.feature_authentication.presentation.register

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
 * DATE: 19/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    onNavigateToVerification: () -> Unit
) {
    val scrollState = rememberScrollState()

    RegisterScreenContent(
        scrollState = scrollState,
        onNavigateToLogin = onNavigateToLogin
    )
}

@ExperimentalComposeUiApi
@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AuthHeaderImage(headerImage = R.drawable.bicycle_auth)
            AuthHeader(
                header = stringResource(id = R.string.create_new_account_question_action),
                subtitle = stringResource(R.string.create_account_message)
            )

            RegisterFormContainer(
                onNameChanged = {},
                onEmailChanged = { },
                onPasswordChanged = {},
                onSubmitForm = {},
                onConfirmationPasswordChanged = {}
            )
        }

        Box(
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        ) {
            AuthQuestionButton(
                question = stringResource(R.string.existing_account_question),
                actionText = stringResource(R.string.existing_account_question_action),
                actionTextColor = Orange600,
                onClick = onNavigateToLogin
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun PreviewRegisterScreenContent() {
    RegisterScreenContent(
        scrollState = rememberScrollState(),
        onNavigateToLogin = {}
    )
}
