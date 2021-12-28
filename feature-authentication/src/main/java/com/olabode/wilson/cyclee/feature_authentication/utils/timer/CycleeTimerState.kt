package com.olabode.wilson.cyclee.feature_authentication.utils.timer

sealed class CycleeTimerState {
    object Idle : CycleeTimerState()
    class Ticking(val millisRemaining: Long, val formattedString: String) : CycleeTimerState()
    object Finished : CycleeTimerState()
}
