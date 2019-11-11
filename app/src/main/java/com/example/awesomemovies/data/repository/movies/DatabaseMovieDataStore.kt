package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie

class DatabaseMovieDataStore (private val movieDao: MovieDao) : MovieDataStore {

    override suspend fun getMovies(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun searchMovies(query: String, voteAverage: Int?): List<Movie> {
        return listOf<Movie>()
    }
}