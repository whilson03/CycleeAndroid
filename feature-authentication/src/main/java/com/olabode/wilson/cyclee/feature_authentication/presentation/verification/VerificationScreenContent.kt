package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.common_ui.ui.getString
import com.olabode.wilson.cyclee.feature_authentication.R
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthButton
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.AuthHeaderImage
import com.olabode.wilson.cyclee.feature_authentication.presentation.components.pinview.PinView

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Suppress("UnusedPrivateMember")
@ExperimentalComposeUiApi
@Composable
fun VerificationScreenContent(
    modifier: Modifier = Modifier,
    uiState: VerificationScreenUiState,
    isLoading: Boolean = false,
    isResendButtonEnabled: Boolean = false,
    isSubmitButtonEnabled: Boolean = false,
    onTokenChanged: (token: String) -> Unit,
    onResendClicked: () -> Unit,
    onSubmitClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        AuthHeaderImage(
            modifier = Modifier.align(Alignment.TopCenter),
            headerImage = R.drawable.bicycle_auth
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp
            ),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                ),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TitleAndMailSection(email = uiState.email?.getString() ?: "")

                PinView(onValueChanged = onTokenChanged)

                VerificationBottomSection(
                    isResendButtonEnabled = isResendButtonEnabled,
                    isSubmitButtonEnabled = isSubmitButtonEnabled,
                    onResendTokenClicked = onResendClicked,
                    onSubmitClicked = onSubmitClicked
                )
            }
        }
    }
}

@Composable
private fun VerificationBottomSection(
    modifier: Modifier = Modifier,
    isResendButtonEnabled: Boolean = false,
    isSubmitButtonEnabled: Boolean = false,
    onResendTokenClicked: () -> Unit,
    onSubmitClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextButton(
            onClick = onResendTokenClicked,
            enabled = isResendButtonEnabled
        ) {
            Text(
                text = "Resend code in 02:00 ?",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        AuthButton(
            text = stringResource(id = R.string.submit),
            enabled = isSubmitButtonEnabled,
            onClick = onSubmitClicked
        )
    }
}

@Composable
private fun TitleAndMailSection(
    modifier: Modifier = Modifier,
    email: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(R.string.mail_verification_title),
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
            style = TextStyle(
                color = Orange600,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = email,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewVerificationScreen() {
    VerificationScreen()
}
