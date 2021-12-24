package com.olabode.wilson.cyclee.feature_authentication.presentation.components.pinview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olabode.wilson.cyclee.common_ui.theme.Orange600
import com.olabode.wilson.cyclee.feature_authentication.R

@Composable
fun PinKeyboard(
    modifier: Modifier = Modifier,
    onClick: (value: Int) -> Unit,
    onDeleteClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val columnModifier = Modifier.weight(1f)

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "1", onClick = onClick)
                PinNumberButton(text = "4", onClick = onClick)
                PinNumberButton(text = "7", onClick = onClick)
            }

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "2", onClick = onClick)
                PinNumberButton(text = "5", onClick = onClick)
                PinNumberButton(text = "8", onClick = onClick)
                PinNumberButton(text = "0", onClick = onClick)
            }

            Column(
                modifier = columnModifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PinNumberButton(text = "3", onClick = onClick)
                PinNumberButton(text = "6", onClick = onClick)
                PinNumberButton(text = "9", onClick = onClick)
            }
        }

        PinDeleteButton(onClick = onDeleteClicked)
    }
}

@Composable
fun PinNumberButton(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 30.sp,
    textColor: Color = Color.Black,
    onClick: (value: Int) -> Unit,
) {
    TextButton(
        modifier = modifier.padding(4.dp),
        onClick = { onClick(text.toInt()) }
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun PinDeleteButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.delete),
    textSize: TextUnit = 15.sp,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Immutable
class PinViewStyle(
    val width: Dp = 60.dp,
    val textStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    ),
    val focusedColor: Color = Orange600,
    val backgroundColor: Color = Color.Gray,
    val unfocusedColour: Color = Color.LightGray,
) {

    companion object {
        val DEFAULT = PinViewStyle()
    }
}

@Composable
fun BuildTextField(
    modifier: Modifier = Modifier,
    text: String,
    style: PinViewStyle = PinViewStyle.DEFAULT,
    onValueChanged: (text: String) -> Unit,
) {
    OutlinedTextField(
        textStyle = style.textStyle,
        readOnly = true,
        modifier = modifier
            .width(style.width)
            .padding(2.dp),
        value = text,
        onValueChange = onValueChanged,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = style.focusedColor,
            backgroundColor = style.backgroundColor,
            unfocusedIndicatorColor = style.unfocusedColour

        ),
    )
}

@Composable
fun PinView(
    modifier: Modifier = Modifier,
    noOfFields: Int = 5,
    onValueChanged: (value: String) -> Unit
) {
    Surface {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var enteredPinCounter by remember { mutableStateOf(0) }
                var verificationCode by rememberSaveable { mutableStateOf("") }
                val focusRequester = remember { List(noOfFields) { FocusRequester() } }
                val firstFocusableIndex = 0
                val lastFocusableIndex = focusRequester.lastIndex
                var nextFocus = focusRequester[firstFocusableIndex]

                BuildPinBoxes(noOfFields, focusRequester, verificationCode)

                DisposableEffect(Unit) {
                    nextFocus.requestFocus()
                    onDispose { }
                }
                PinKeyboard(
                    onClick = {
                        if (enteredPinCounter < noOfFields) {
                            nextFocus =
                                if (enteredPinCounter == lastFocusableIndex) {
                                    focusRequester[lastFocusableIndex]
                                } else {
                                    focusRequester[enteredPinCounter + 1]
                                }
                            verificationCode += it.toString()
                            enteredPinCounter++
                            nextFocus.requestFocus()
                            onValueChanged(verificationCode)
                        }
                    },
                    onDeleteClicked = {
                        if (enteredPinCounter > firstFocusableIndex) {
                            enteredPinCounter--
                            verificationCode =
                                verificationCode.removeRange(
                                    enteredPinCounter,
                                    verificationCode.length
                                )
                            nextFocus =
                                if (enteredPinCounter == firstFocusableIndex) {
                                    focusRequester[firstFocusableIndex]
                                } else {
                                    focusRequester[enteredPinCounter - 1]
                                }

                            nextFocus.requestFocus()
                            onValueChanged(verificationCode)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BuildPinBoxes(
    noOfFields: Int,
    focusRequester: List<FocusRequester>,
    verificationCode: String
) {
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
        for (i in 0 until noOfFields) {
            BuildTextField(
                modifier = Modifier.focusRequester(focusRequester[i]),
                onValueChanged = {},
                text = if (verificationCode.length < i + 1) "" else verificationCode[i].toString()
            )
        }
    }
}

@Preview
@Composable
fun PreviewPinScreen() {
    PinView(
        modifier = Modifier.fillMaxSize()
    ) {}
}

@Preview
@Composable
fun PreviewPinKeyboard() {
    PinKeyboard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        onDeleteClicked = {}
    )
}

@Preview
@Composable
fun PreviewPinNumberButton() {
    PinNumberButton(
        modifier = Modifier,
        text = "6"
    ) {}
}
