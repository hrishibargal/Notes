package com.develope.notes.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NotesPreference @Inject constructor(@ApplicationContext private val context: Context) : Preference {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val IS_DARK_MODE = booleanPreferencesKey("IS_DARK_MODE")

    override val isDarkModeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DARK_MODE] ?: false
        }

    override suspend fun onDarkModeToggled() {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = !(preferences[IS_DARK_MODE] ?: false)
        }
    }
}