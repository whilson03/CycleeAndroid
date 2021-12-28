package com.olabode.wilson.cyclee.feature_authentication.utils.timer

import android.os.CountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 28/12/2021
 * EMAIL: whilson03@gmail.com
 */

class VerificationCountDownTimerImpl @Inject constructor(
    val millis: Long,
    interval: Long = 1000L
) : VerificationCountDownTimer {

    private val _timerState = MutableStateFlow<CycleeTimerState>(CycleeTimerState.Idle)
    override val timerState: StateFlow<CycleeTimerState> = _timerState

    private var countDownTimer = object : CountDownTimer(millis, interval) {
        override fun onTick(millisUntilFinished: Long) {
            val minutes = (millisUntilFinished / 1000).toInt() / 60
            val seconds = (millisUntilFinished / 1000).toInt() % 60

            _timerState.value = CycleeTimerState.Ticking(
                millisUntilFinished,
                String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            )
        }

        override fun onFinish() {
            _timerState.value = CycleeTimerState.Finished
        }
    }

    override fun startTimer() {
        countDownTimer.start()
    }

    override fun stopTimer() {
        countDownTimer.onFinish()
        countDownTimer.cancel()
    }
}
