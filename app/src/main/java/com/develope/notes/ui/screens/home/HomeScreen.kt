package com.develope.notes.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develope.notes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToEditScreen: (noteId: Int) -> Unit
) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val allNotes by viewModel.notes.collectAsState(initial = listOf())
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.all_notes), fontSize = 28.sp) },

                )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToEditScreen(-1) }) {
                Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = null)
            }
        }
    )
    {
        Surface(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                    },
                    onSearch = {},
                    active = false,
                    onActiveChange = {},
                    placeholder = { Text(text = "Search Your Notes") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = ""
                        )
                    }
                ) {

                }

                LazyColumn {
                    items(allNotes.filter { item ->
                        item.title.contains(searchQuery)
                    }) { note ->
                        NoteItem(note, navigateToEditScreen)
                    }
                }

            }
        }
    }
}