package com.develope.notes.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.develope.notes.R

sealed class NoteAppScreens(val route: String, @StringRes val resourceId: Int?, @DrawableRes val icon: Int? ) {
    object Home: NoteAppScreens("home", R.string.home, R.drawable.outline_home)
    object Settings: NoteAppScreens("settings", R.string.settings, R.drawable.outline_settings)
    object AddEditScreen: NoteAppScreens("addEditScreen", null, null)
}
