package com.develope.notes.ui.screens.addEditScreens.repository

import com.develope.notes.dto.Note
import com.develope.notes.room.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddEditScreenRepository @Inject constructor(private val dao: NotesDao) {

    fun getNoteById(noteId: Int?): Flow<Note?> = dao.getNoteById(noteId)

    suspend fun addNote(note: Note) = dao.addNote(note)

    suspend fun updateNote(note: Note) = dao.updateNote(note)
}