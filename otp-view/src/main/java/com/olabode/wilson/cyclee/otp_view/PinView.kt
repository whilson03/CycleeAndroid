package com.olabode.wilson.cyclee.otp_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

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

@Preview
@Composable
fun PreviewPinScreen() {
    PinView(
        modifier = Modifier.fillMaxSize()
    ) {}
}
