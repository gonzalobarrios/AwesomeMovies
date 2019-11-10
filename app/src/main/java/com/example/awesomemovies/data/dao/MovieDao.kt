package com.example.awesomemovies.data.dao

import androidx.room.*
import com.example.awesomemovies.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :query")
    suspend fun getMoviesByQuery(query: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg movies: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Transaction
    suspend fun insertOrUpdate(movie: Movie) {
        if (insert(movie).equals(-1L)) {
            update(movie)
        }
    }
}