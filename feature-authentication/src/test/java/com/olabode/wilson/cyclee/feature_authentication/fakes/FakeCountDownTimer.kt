package com.olabode.wilson.cyclee.feature_authentication.fakes

import com.olabode.wilson.cyclee.feature_authentication.utils.timer.CycleeTimerState
import com.olabode.wilson.cyclee.feature_authentication.utils.timer.VerificationCountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 28/12/2021
 * EMAIL: whilson03@gmail.com
 */

class FakeCountDownTimer : VerificationCountDownTimer {
    private val state = MutableStateFlow<CycleeTimerState>(CycleeTimerState.Idle)
    override val timerState: StateFlow<CycleeTimerState>
        get() = state

    override fun startTimer() {
        setTimerState(CycleeTimerState.Ticking(0L, ""))
    }

    override fun stopTimer() {
        setTimerState(CycleeTimerState.Finished)
    }

    private fun setTimerState(state: CycleeTimerState) {
        this.state.value = state
    }
}
