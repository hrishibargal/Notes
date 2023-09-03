package com.develope.notes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.develope.notes.dto.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id=:noteId ")
    fun getNoteById(noteId: Int?): Flow<Note?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Query("DELETE FROM note")
    suspend fun deleteNotes()

    @Update
    suspend fun updateNote(noteModel: Note)

    @Delete
    suspend fun deleteNote(noteModel: Note)
}