package com.olabode.wilson.cyclee.core.ui.timer

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 24/12/2021
 * EMAIL: whilson03@gmail.com
 */

sealed class CycleeTimerState {
    object Idle : CycleeTimerState()
    class Ticking(val millisRemaining: Long) : CycleeTimerState()
    object Finished : CycleeTimerState()
}
