package net.l1ngdtkh3.data.datasources.datastore

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import net.l1ngdtkh3.data.BuildConfig
import net.l1ngdtkh3.data.datasources.db.MoviesType
import javax.inject.Inject

class SettingsDataStoreImpl @Inject constructor(@ApplicationContext val context: Context) : SettingsDataStore {

    companion object {
        private val LAST_UPDATED_NOW_PLAYING = longPreferencesKey("last_update_now_playing")
        private val LAST_UPDATED_POPULAR = longPreferencesKey("last_update_popular")
        private val LAST_UPDATED_UP_COMING = longPreferencesKey("last_update_up_coming")
    }

    private val dataStore = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(BuildConfig.LIBRARY_PACKAGE_NAME + "_client_settings")
    }

    private suspend inline fun <reified T> updateValue(key: Preferences.Key<T>, value: T?) {
        dataStore.edit {
            if (value != null) it[key] = value
        }
    }

    override suspend fun setLastTimeUpdate(type: MoviesType, value: Long) {
        when (type) {
            MoviesType.NOW_PLAYING -> updateValue(LAST_UPDATED_NOW_PLAYING, value)
            MoviesType.UP_COMING -> updateValue(LAST_UPDATED_UP_COMING, value)
            MoviesType.POPULAR -> updateValue(LAST_UPDATED_POPULAR, value)
        }
    }

    override suspend fun getLastTimeUpdate(type: MoviesType): Long {
        val pref = when (type) {
            MoviesType.NOW_PLAYING -> LAST_UPDATED_NOW_PLAYING
            MoviesType.UP_COMING -> LAST_UPDATED_UP_COMING
            MoviesType.POPULAR -> LAST_UPDATED_POPULAR
        }
        return dataStore.data.map { it[pref] }.first() ?: 0
    }
}

interface SettingsDataStore : LastTimeUpdate

interface LastTimeUpdate {
    suspend fun setLastTimeUpdate(type: MoviesType, value: Long)
    suspend fun getLastTimeUpdate(type: MoviesType): Long
}