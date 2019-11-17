package com.example.awesomemovies.data.model

import androidx.room.*
import org.threeten.bp.ZonedDateTime

@Entity(indices = arrayOf(Index(value = ["id"],
    unique = true)))
data class Movie (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @Ignore val genres: List<Genre>)

{
    constructor(id: Int, title: String, voteAverage: Double, posterPath: String, overview: String, releaseDate: String) : this(
        id,
        title,
        voteAverage,
        posterPath,
        overview,
        releaseDate,
        listOf<Genre>()
    )

}