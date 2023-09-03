package com.develope.notes.room

import androidx.room.TypeConverter
import com.develope.notes.utils.DATE_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object RoomConverters {
    private val dateDateTimeFormator = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())

    @TypeConverter
    fun convertDateToString(date: Date): String {
        return dateDateTimeFormator.format(date)
    }

    @TypeConverter
    fun convertStringToDate(date: String): Date {
       return dateDateTimeFormator.parse(date)
    }

}