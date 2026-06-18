package com.poomani.tv.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class SettingsDataStore @Inject constructor(private val context: Context) {
    companion object {
        val BACKEND_URL = stringPreferencesKey("backend_url")
        val SELECTED_PROVIDER_ID = stringPreferencesKey("provider_id")
        val CACHE_SIZE_MB = intPreferencesKey("cache_size")
        val AUTO_PLAY = booleanPreferencesKey("auto_play")
    }

    val backendUrl: Flow<String> = context.dataStore.data.map { it[BACKEND_URL] ?: "http://136.185.6.102:3001" }
    val selectedProviderId: Flow<String?> = context.dataStore.data.map { it[SELECTED_PROVIDER_ID] }
    val autoPlay: Flow<Boolean> = context.dataStore.data.map { it[AUTO_PLAY] ?: true }

    suspend fun saveBackendUrl(url: String) { context.dataStore.edit { it[BACKEND_URL] = url } }
    suspend fun saveProviderId(id: String) { context.dataStore.edit { it[SELECTED_PROVIDER_ID] = id } }
}
