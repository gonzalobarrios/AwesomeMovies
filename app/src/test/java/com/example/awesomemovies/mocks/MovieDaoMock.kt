package com.example.awesomemovies.mocks

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie

class MovieDaoMock: MovieDao {
    private val movie =  Movie(170,"Title",5.0, "path", "description", "10/05/2019", listOf())
    override suspend fun delete(movie: Movie) {
    }

    override suspend fun deleteWhenMax(max: Int) {
    }

    override suspend fun getAll(): List<Movie> {
        return listOf(movie)
    }

    override suspend fun getMovie(id: Int): Movie {
        return movie
    }

    override suspend fun getMoviesByQuery(query: String): List<Movie> {
        return listOf(movie)
    }

    override suspend fun insert(vararg movies: Movie) {
    }

    override suspend fun update(movie: Movie) {
    }

}