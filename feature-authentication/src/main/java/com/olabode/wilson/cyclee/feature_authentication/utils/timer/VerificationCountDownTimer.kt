package com.olabode.wilson.cyclee.feature_authentication.utils.timer

import kotlinx.coroutines.flow.StateFlow

interface VerificationCountDownTimer {

    val timerState: StateFlow<CycleeTimerState>

    fun startTimer()

    fun stopTimer()
}
