package com.olabode.wilson.cyclee.store

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun clearStorage()

    fun <T> getValueAsFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T>

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T)
}
