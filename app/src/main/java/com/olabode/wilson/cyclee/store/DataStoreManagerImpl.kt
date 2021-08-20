package com.olabode.wilson.cyclee.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.olabode.wilson.cyclee.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@InternalCoroutinesApi
class DataStoreManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE_NAME)

    override suspend fun clearStorage() {
        context.dataStore.edit { it.clear() }
    }

    override fun <T> getValueAsFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return context.dataStore.getValueAsFlow(key, defaultValue)
    }

    override suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.setValue(key, value)
    }

    /***
     * handy function to save key-value pairs in Preference. Sets or updates the value in Preference
     * @param key used to identify the preference
     * @param value the value to be saved in the preference
     */
    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            // save the value in prefs
            preferences[key] = value
        }
    }

    /***
     * helper function to return Preference value based on the Preference key
     * @param key  used to identify the preference
     * @param defaultValue value in case the Preference does not exists
     * @throws Exception if there is some error in getting the value
     * @return [Flow] of [T]
     */
    private fun <T> DataStore<Preferences>.getValueAsFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                // we try again to store the value in the map operator
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // return the default value if it doesn't exist in the storage
            preferences[key] ?: defaultValue
        }
    }
}
