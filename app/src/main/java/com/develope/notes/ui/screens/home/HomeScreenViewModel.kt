package com.develope.notes.ui.screens.home

import androidx.lifecycle.ViewModel
import com.develope.notes.dto.Note
import com.develope.notes.ui.screens.home.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(noteRepository: NoteRepository): ViewModel() {

       val notes: Flow<List<Note>> = noteRepository.getAllNotes().flowOn(Dispatchers.IO)
}