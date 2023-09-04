package com.develope.notes.ui.screens.settings

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.develope.notes.R
import com.develope.notes.ui.theme.NotesTheme

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val isDarkThemeEnabled by viewModel.isDarkModeEnabled.collectAsState()

    Scaffold {
        Surface(modifier = Modifier.padding(it)) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Text(text = "App Settings")
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingSwitchCard(
                        icon = painterResource(id = R.drawable.moon_icon),
                        text = "Dark Mode",
                        isChecked = isDarkThemeEnabled.isDarkModeEnabled,
                        onCheckedChange = {
                            viewModel.setToggleDarkModeValue()
                        }
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    NotesTheme {
        SettingsScreen()
    }
}