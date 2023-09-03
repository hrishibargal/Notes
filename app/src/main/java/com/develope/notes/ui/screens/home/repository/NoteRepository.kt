package com.develope.notes.ui.screens.home.repository

import com.develope.notes.dto.Note
import com.develope.notes.room.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NotesDao) {

    fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    suspend fun getNoteById(noteId: Int): Flow<Note?> {
        return dao.getNoteById(noteId)
    }

    suspend fun addNote(note: Note) {
        dao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    suspend fun deleteNode(note: Note) {
        dao.deleteNote(note)
    }

}