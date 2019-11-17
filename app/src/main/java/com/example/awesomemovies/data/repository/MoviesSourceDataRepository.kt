package com.example.awesomemovies.data.repository

import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.repository.movies.MoviesDataStoreFactory


class MoviesSourceDataRepository (var factory: MoviesDataStoreFactory) : MoviesSourceRepository{

    override suspend fun getMovies(): List<Movie> {
        return factory.moviesDataStoreFactory.getMovies()
    }

    override suspend fun searchMovies(query: String, voteAverage: Int?): List<Movie> {
        return factory.moviesDataStoreFactory.searchMovies(query)
    }

}