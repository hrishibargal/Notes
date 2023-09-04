package com.develope.notes.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.develope.notes.ui.navigation.NoteAppScreens
import com.develope.notes.ui.screens.settings.SettingsScreen
import com.develope.notes.ui.screens.addEditScreens.AddEditNote

@Composable
fun HomeNavigation() {
    val homeScreens = listOf(
        NoteAppScreens.Home,
        NoteAppScreens.Settings
    )

    val screensWithoutNav = listOf(
        "${NoteAppScreens.AddEditScreen.route}/{noteId}"
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntry?.destination
            if (currentDestination?.route !in screensWithoutNav) {
                BottomAppBar {
                    homeScreens.forEach { screen ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon ?: -1),
                                    contentDescription = null
                                )
                            })
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NoteAppScreens.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(NoteAppScreens.Home.route) {
                HomeScreenEntry { noteId ->
                    navController.navigate("${NoteAppScreens.AddEditScreen.route}/${noteId}")
                }
            }
            composable(NoteAppScreens.Settings.route) {
                SettingsScreen()
            }
            composable(
                route = "${NoteAppScreens.AddEditScreen.route}/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStack ->
                val noteId = backStack.arguments?.getInt("noteId") ?: -1
                AddEditNote(noteId = noteId) {
                    navController.popBackStack()
                }
            }
        }
    }
}