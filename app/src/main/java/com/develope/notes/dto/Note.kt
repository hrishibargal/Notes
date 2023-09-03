package com.develope.notes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.develope.notes.room.RoomConverters
import java.util.Date

@Entity(tableName = "note")
@TypeConverters(RoomConverters::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var note: String,
    var dateTime: Date
)
