package com.example.awesomemovies.data.model

import androidx.room.*
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie

@Entity(tableName = "movie_genre_join",
    primaryKeys = arrayOf("movie_id","genre_id"),
    indices = arrayOf(Index(value = ["movie_id", "genre_id"],
        unique = true)),
    foreignKeys = arrayOf(
        ForeignKey(entity = Movie::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movie_id")),
        ForeignKey(entity = Genre::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("genre_id"))
    ))

data class MovieGenreJoin (
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "genre_id")
    val genreId: Int
)