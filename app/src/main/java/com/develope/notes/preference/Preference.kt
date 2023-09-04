package com.develope.notes.preference

import kotlinx.coroutines.flow.Flow

interface Preference {
    suspend fun onDarkModeToggled()
    val isDarkModeEnabled: Flow<Boolean>
}