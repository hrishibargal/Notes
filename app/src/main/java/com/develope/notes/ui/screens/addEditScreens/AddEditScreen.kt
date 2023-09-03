package com.develope.notes.ui.screens.addEditScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.develope.notes.dto.Note
import com.develope.notes.utils.DATE_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNote(
    noteId: Int,
    navigateBack: () -> Unit
) {

    val viewModel = hiltViewModel<AddEditScreenViewModel>()
    val noteModel by viewModel.note.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getNoteById(noteId)
    }
    val dateTimeFormatter = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
    dateTimeFormatter.format(Calendar.getInstance().time)
    var characterCount by remember {
        mutableStateOf(0)
    }
    characterCount = noteModel.title.length + noteModel.note.length

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    if (noteModel.title.isNotEmpty() && noteModel.note.isNotEmpty()) {
                        IconButton(onClick = {
                            if (noteId == -1) {
                                viewModel.addNote(
                                    Note(
                                        title = noteModel.title,
                                        note = noteModel.note,
                                        dateTime = Calendar.getInstance().time
                                    )
                                )
                            } else {
                                viewModel.updateNoteModel(
                                    noteModel.copy(
                                        title = noteModel.title,
                                        note = noteModel.note,
                                        dateTime = Calendar.getInstance().time
                                    )
                                )
                            }
                            navigateBack()
                        }) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null)
                        }
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = noteModel.title.orEmpty(),
                    onValueChange = { newTitle ->
                        viewModel.updateTitle(newTitle)
                        characterCount = newTitle.length + noteModel.note.length
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = "Title")
                    }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value =
                    "${dateTimeFormatter.format(noteModel.dateTime)} | $characterCount characters",
                    onValueChange = {},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    readOnly = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = noteModel.note,
                    onValueChange = {description->
                        viewModel.updateNote(description)
                        characterCount = noteModel.title.length + description.length},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = "Description..")
                    }
                )
            }
        }
    }

}
