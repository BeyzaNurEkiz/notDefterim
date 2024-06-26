package com.example.notdefterim.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notdefterim.data.local.model.Note
import kotlinx.coroutines.flow.Flow


@Dao   //Data Access Object: veritabanına erişimi sağlar.
interface NoteDao {

    //Note üzerinde oluşturduğumuz verileri burada sqlite ile çekiyoruz.
    @Query("SELECT * FROM notes ORDER BY createdDate")
    fun getAllNotes(): Flow<List<Note>>
    @Query("SELECT * FROM notes WHERE id=:id" )
    fun getNoteById(id:Long): Flow<Note>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
     fun insertNote(note: Note)

    @Update(onConflict=OnConflictStrategy.REPLACE)
     fun update(note: Note)

    @Query("DELETE FROM notes WHERE id=:id")
     fun delete(id:Long)

    @Query("SELECT * FROM notes WHERE isBookMarked=1 ORDER BY createdDate DESC")
    fun getBookmarkedNotes(): Flow<List<Note>>
}