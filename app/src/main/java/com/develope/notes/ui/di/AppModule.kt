package com.develope.notes.ui.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.develope.notes.room.NotesDao
import com.develope.notes.room.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java, "notes_db"
        ).build()
    }

    @Provides
    fun provideNotesDao(notesDb: NotesDatabase): NotesDao {
        return notesDb.notesDao()
    }
}