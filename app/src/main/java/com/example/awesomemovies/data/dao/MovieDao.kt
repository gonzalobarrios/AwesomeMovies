package com.example.awesomemovies.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.awesomemovies.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Insert
    suspend fun insertAll(vararg notes: Movie)

    @Delete
    suspend fun delete(note: Movie)
}