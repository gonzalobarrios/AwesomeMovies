package com.example.awesomemovies.mocks

import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.MovieGenreJoin

class MovieGenreDaoMock: MovieGenreJoinDao {
    private val movieGenre = MovieGenreJoin(7,7)
    private val genre = Genre(7,"El genero")
    private val movie =  Movie(7,"Title",5.0, "path", "description", "10/05/2019", listOf())

    override suspend fun deleteRelationForMovie(movieId: Int) {
    }

    override suspend fun getGenreJoin(): List<MovieGenreJoin> {
        return listOf(movieGenre)
    }

    override suspend fun getGenresForMovie(movieId: Int): Array<Genre> {
        return arrayOf(genre)
    }

    override suspend fun getMoviesForGenre(genre: Int): Array<Movie> {
        return arrayOf(movie)
    }

    override suspend fun insert(vararg moviesGenreJoin: MovieGenreJoin) {
    }
}
