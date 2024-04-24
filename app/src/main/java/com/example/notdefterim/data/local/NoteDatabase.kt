package com.example.notdefterim.data.local

import com.example.notdefterim.data.local.model.Note
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notdefterim.data.local.converters.DateConverter

@TypeConverters(value=[DateConverter::class])
@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao:NoteDao
}