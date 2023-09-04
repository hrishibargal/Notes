package com.develope.notes.ui.screens.addEditScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develope.notes.dto.Note
import com.develope.notes.ui.screens.addEditScreens.repository.AddEditScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddEditScreenViewModel @Inject constructor(
    private val repository: AddEditScreenRepository
) : ViewModel() {
    private val _note = MutableStateFlow(Note(id = 0, "","", Date()))
    val note = _note.asStateFlow()
    fun getNoteById(noteId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNoteById(noteId).collect {
                if (it != null) {
                    _note.value = it
                } else {
                    _note.value = Note(title =  "", note = "", dateTime = Date())
                }
            }
        }
    }

    fun updateTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _note.value = _note.value.copy(title = title)
        }
    }

    fun updateNote(note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _note.value = _note.value.copy(note = note)
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNoteModel(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }
}