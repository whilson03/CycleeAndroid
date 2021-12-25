package com.olabode.wilson.cyclee.core.utils

import android.os.CountDownTimer
import com.olabode.wilson.cyclee.core.ui.timer.CycleeTimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

class CycleeCountDownTimer(val millis: Long, interval: Long = 10L) {
    private val _timerState = MutableStateFlow<CycleeTimerState>(CycleeTimerState.Idle)
    val timerState: StateFlow<CycleeTimerState> = _timerState

    private var countDownTimer = object : CountDownTimer(millis, interval) {
        override fun onTick(millisUntilFinished: Long) {
            _timerState.value = CycleeTimerState.Ticking(millisUntilFinished)
        }

        override fun onFinish() {
            _timerState.value = CycleeTimerState.Finished
        }
    }

    fun start() {
        countDownTimer.start()
    }

    fun cancel() {
        countDownTimer.onFinish()
        countDownTimer.cancel()
    }
}
