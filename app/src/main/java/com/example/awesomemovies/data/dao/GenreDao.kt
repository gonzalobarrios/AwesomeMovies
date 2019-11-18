package com.example.awesomemovies.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.awesomemovies.data.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<Genre>

    @Query("SELECT * FROM genre where id IN (:ids)")
    suspend fun getAllGenresById(ids: List<Int>): List<Genre>

    @Query("SELECT * FROM genre WHERE id IS :id")
    suspend fun get(id: Int): Genre

    @Insert
    suspend fun insertAll(vararg genres: Genre)

    @Delete
    suspend fun delete(genre: Genre)
}