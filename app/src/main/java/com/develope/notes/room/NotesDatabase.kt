package com.develope.notes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.develope.notes.dto.Note
import javax.inject.Singleton

@Singleton
@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}