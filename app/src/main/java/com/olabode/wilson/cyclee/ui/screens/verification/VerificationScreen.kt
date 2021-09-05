package com.olabode.wilson.cyclee.ui.screens.verification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.R
import com.olabode.wilson.cyclee.ui.component.AuthButton
import com.olabode.wilson.cyclee.ui.component.pinview.PinView
import com.olabode.wilson.cyclee.ui.theme.Orange600

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/09/2021
 * EMAIL: whilson03@gmail.com
 */
@ExperimentalComposeUiApi
@Composable
fun VerificationScreen() {
    VerificationScreenContent()
}

@ExperimentalComposeUiApi
@Composable
fun VerificationScreenContent() {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.bicycle_auth),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp
                    )
                )
                .background(Color.White)
                .padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TitleAndMailSection()

            PinView {
            }
            VerificationBottomSection() {}
        }
    }
}

@Composable
private fun VerificationBottomSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = "Resend code in 02:00 ?",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        AuthButton(
            text = stringResource(id = R.string.submit)
        ) {}
    }
}

@Composable
private fun TitleAndMailSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.mail_verification_title),
            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 4.dp),
            style = TextStyle(
                color = Orange600,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = "dev@gmail.com",
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
