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


    @Query("DELETE FROM movie where id NOT IN (SELECT id from movie ORDER BY id DESC LIMIT :max)")
    suspend fun deleteWhenMax(max: Int)

    @Update
    suspend fun update(movie: Movie)

    @Transaction
    suspend fun controlledInsert(vararg movie: Movie) {
        insert(*movie)
        deleteWhenMax(300)
    }
}