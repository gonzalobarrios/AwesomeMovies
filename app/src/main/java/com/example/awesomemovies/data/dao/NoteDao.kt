package com.barriosartola.awesomeapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.barriosartola.awesomeapp.data.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Delete
    suspend fun delete(note: Note)
}