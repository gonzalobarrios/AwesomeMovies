package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.MovieService

class CloudMoviesDataStore (private val movieService: MovieService) : MovieDataStore {

    suspend fun discoverMovies(voteAverageMin: Int? = null, voteAverageMax: Int? = null): List<Movie> {
        return movieService.discoverMovies(voteAverageMin, voteAverageMax).results
    }

    suspend fun getMovies(): List<Movie> {
        return movieService.discoverMovies(vote_average_min = null, vote_average_max = null).results
    }

    suspend fun searchMovies(query: String): List<Movie> {
        return movieService.searchMovies(query).results
    }

    override suspend fun getMovie(id: Int): Movie{
        return movieService.getMovie(id)
    }


}