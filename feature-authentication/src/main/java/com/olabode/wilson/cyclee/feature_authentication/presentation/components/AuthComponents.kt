package com.olabode.wilson.cyclee.feature_authentication.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olabode.wilson.cyclee.common_ui.theme.Grey600
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.common_ui.theme.Typography

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 18/09/2021
 * EMAIL: whilson03@gmail.com
 */

@Composable
fun AuthHeader(
    modifier: Modifier = Modifier,
    header: String,
    subtitle: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = header,
            style = Typography.h5,
            color = Orange600
        )
        Text(
            text = subtitle,
            style = Typography.caption,
            color = Grey600
        )
    }
}

@Composable
@Preview
fun PreviewAuthHeader() {
    AuthHeader(
        header = "Header",
        subtitle = "subtitle"
    )
}

@Composable
fun AuthQuestionButton(
    modifier: Modifier = Modifier,
    question: String,
    actionText: String,
    actionTextColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
            )
        ) {
            append("$question? ")
        }
        withStyle(
            style = SpanStyle(
                color = actionTextColor,
            )
        ) {
            append(actionText)
        }
    }

    TextButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Text(
            text = annotatedText,
            textAlign = TextAlign.Center,
            style = Typography.caption,
        )
    }
}

@Composable
@Preview
fun PreviewAuthQuestionButton() {
    AuthQuestionButton(
        question = "have account",
        actionText = "log in",
        actionTextColor = Color.Red
    ) {}
}

@Composable
fun AuthHeaderImage(
    modifier: Modifier = Modifier,
    @DrawableRes headerImage: Int
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top),
        painter = painterResource(id = headerImage),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewAuthHeaderImage() {
    // todo
    // AuthHeaderImage(headerImage = R.drawable.cyclist_riding_auth)
}
