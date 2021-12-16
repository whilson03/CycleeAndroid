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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeader
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeaderImage
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthQuestionButton

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 08/12/2021
 * EMAIL: whilson03@gmail.com
 */

@ExperimentalComposeUiApi
@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    viewState: RegisterViewState,
    viewModel: RegisterViewModel,
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
                viewState = viewState,
                onFirstNameChanged = viewModel::firstNameChanged,
                onLastNameChanged = viewModel::lastNameChanged,
                onEmailChanged = viewModel::emailChanged,
                onPasswordChanged = viewModel::passwordChanged,
                onSubmitForm = viewModel::registerButtonClicked,
                onConfirmationPasswordChanged = viewModel::confirmPasswordChanged
            )
        }

        Box(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
            AuthQuestionButton(
                question = stringResource(R.string.existing_account_question),
                actionText = stringResource(R.string.existing_account_question_action),
                actionTextColor = Orange600,
                onClick = onNavigateToLogin,
                enabled = viewState.inputsEnabled
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
        onNavigateToLogin = {},
        viewState = RegisterViewState.Initial,
        viewModel = viewModel()
    )
}
