package com.olabode.wilson.cyclee.feature_authentication.presentation.verification

import com.olabode.wilson.cyclee.feature_authentication.domain.model.verification.VerificationToken
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 25/12/2021
 * EMAIL: whilson03@gmail.com
 */

class VerificationScreenViewModelTest {

    private lateinit var testRobot: VerificationScreenViewModelRobot

    @Before
    fun setUp() {
        testRobot = VerificationScreenViewModelRobot()
    }

    @Test
    fun testUpdateToken() = runBlockingTest {
        val token = VerificationToken("12345")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = true
        )

        val viewStates = listOf(initialState, tokenEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(token.token)
                },
                viewStates = viewStates,
            )
    }

    @Test
    fun testIncompleteTokenInputDisablesSubmitButton() = runBlockingTest {
        val token = VerificationToken("123")

        val initialState = VerificationScreenUiState()

        val tokenEnteredState = VerificationScreenUiState(
            token = token,
            isSendButtonEnabled = false
        )

        val viewStates = listOf(initialState, tokenEnteredState)

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterToken(token.token)
                },
                viewStates = viewStates,
            )
    }
}
