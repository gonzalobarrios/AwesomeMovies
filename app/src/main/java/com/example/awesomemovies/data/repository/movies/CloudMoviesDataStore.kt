package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.MovieService

class CloudMoviesDataStore (private val movieService: MovieService, private val movieDao: MovieDao) : MovieDataStore {

    override suspend fun getMovies(): List<Movie> {
        val movies = movieService.discoverMovies().results
        if(movies.isNotEmpty()){
            saveMovies(movies)
        }
        return movies
    }

    override suspend fun searchMovies(query: String, voteAverage: Int?): List<Movie> {
        val movies = movieService.searchMovies(query, voteAverage).results
        if(movies.isNotEmpty()){
            saveMovies(movies)
        }
        return movies
    }

    private suspend fun saveMovies(movies: List<Movie>){
        movieDao.controlledInsert(*movies.toTypedArray())
    }
}