package com.develope.notes.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develope.notes.preference.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val preference: Preference): ViewModel() {

    private val _isDarkModeEnabled = MutableStateFlow(SettingsData())
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preference.isDarkModeEnabled.collect {
                _isDarkModeEnabled.value = _isDarkModeEnabled.value.copy(isDarkModeEnabled = it)
            }
        }
    }

    fun setToggleDarkModeValue() {
        viewModelScope.launch(Dispatchers.IO) {
            preference.onDarkModeToggled()
        }
    }


}

data class SettingsData(
    var isDarkModeEnabled: Boolean = false
)