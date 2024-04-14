package com.example.notdefterim.data.local.model

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY createdDate")
    fun getAllNotes(): Flow<List<Note>>
    @Query("SELECT * FROM notes " )
    fun getNoteById(id:Long): Flow<Note>

}