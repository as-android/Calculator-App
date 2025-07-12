package com.example.calculator

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "settings")

object FirstLaunchPrefs {
    private val FIRST_LAUNCH_KEY = booleanPreferencesKey("first_launch_done")

    suspend fun isFirstLaunch(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[FIRST_LAUNCH_KEY] != true
    }

    suspend fun setFirstLaunchDone(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[FIRST_LAUNCH_KEY] = true
        }
    }
}

