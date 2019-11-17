package com.example.awesomemovies.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.MovieGenreJoin

@Dao
interface MovieGenreJoinDao {
    @Insert
    suspend fun insert(vararg moviesGenreJoin: MovieGenreJoin)

    @Query("""
           SELECT * FROM movie
           INNER JOIN movie_genre_join
           ON movie.id=movie_genre_join.movie_id
           WHERE movie_genre_join.genre_id=:genre
           """)
    suspend fun getMoviesForGenre(genre: Int): Array<Movie>

    @Query("""
           SELECT * FROM genre
           INNER JOIN movie_genre_join
           ON genre.id=movie_genre_join.genre_id
           WHERE movie_genre_join.movie_id=:movieId
           """)
    suspend fun getGenresForMovie(movieId: Int): Array<Genre>
}