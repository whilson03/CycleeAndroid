package com.olabode.wilson.cyclee.session

import kotlinx.coroutines.flow.Flow

interface SessionManager {

    val isAppFirstTimeLaunched: Flow<Boolean>

    suspend fun setIsNotFirstTimeLaunched()
}
