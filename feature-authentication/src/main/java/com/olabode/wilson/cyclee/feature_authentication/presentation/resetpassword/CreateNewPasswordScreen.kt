package com.olabode.wilson.cyclee.feature_authentication.presentation.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthButton
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeaderImage
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.PasswordTextField

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 23/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@Composable
fun CreateNewPasswordScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
) {
    EnterPasswordScreenContent(
        onPasswordChanged = {},
        onConfirmPasswordChanged = {},
        onSubmitForm = {}
    )
}

@Composable
fun EnterPasswordScreenContent(
    modifier: Modifier = Modifier,
    onPasswordChanged: (password: String) -> Unit,
    onConfirmPasswordChanged: (password: String) -> Unit,
    onSubmitForm: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AuthHeaderImage(
            modifier = Modifier.align(Alignment.TopCenter),
            headerImage = R.drawable.cyclist_riding_auth
        )
        Surface(
            elevation = 8.dp,
            shape = RoundedCornerShape(
                topEnd = 40.dp,
                topStart = 40.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .align(Alignment.BottomCenter)
        ) {
            Column {
                EnterNewPasswordForm(
                    onPasswordChanged = onPasswordChanged,
                    onConfirmPasswordChanged = onConfirmPasswordChanged,
                    onSubmitForm = onSubmitForm
                )
            }
        }
    }
}

@Composable
fun EnterNewPasswordForm(
    modifier: Modifier = Modifier,
    onPasswordChanged: (password: String) -> Unit,
    onConfirmPasswordChanged: (password: String) -> Unit,
    onSubmitForm: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.create_new_password),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Orange600,
            fontWeight = FontWeight.SemiBold
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
            onValueChange = onConfirmPasswordChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButton(
            text = stringResource(R.string.create),
            onClick = onSubmitForm
        )
    }
}

@Preview
@Composable
fun PreviewEnterNewPasswordScreen() {
    CreateNewPasswordScreen {
    }
}
