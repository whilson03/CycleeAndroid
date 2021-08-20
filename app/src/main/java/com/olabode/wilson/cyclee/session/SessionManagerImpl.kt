package com.olabode.wilson.cyclee.session

import com.olabode.wilson.cyclee.store.DataStoreManager
import com.olabode.wilson.cyclee.utils.PreferenceKeys
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InternalCoroutinesApi
class SessionManagerImpl @Inject constructor(
    private val dataStore: DataStoreManager
) : SessionManager {

    override val isAppFirstTimeLaunched: Flow<Boolean>
        get() = dataStore.getValueAsFlow(PreferenceKeys.IsAppFirstLaunched, true)

    override suspend fun setIsNotFirstTimeLaunched() {
        dataStore.storeValue(PreferenceKeys.IsAppFirstLaunched, false)
    }
}
