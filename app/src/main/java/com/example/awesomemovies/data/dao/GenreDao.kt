package com.example.awesomemovies.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.awesomemovies.data.model.Genre
interface GenreDao {

    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<Genre>

    @Insert
    suspend fun insertAll(vararg genres: Genre)

    @Delete
    suspend fun delete(genre: Genre)
}