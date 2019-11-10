package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.MovieService

class CloudMoviesDataStore (private var movieService: MovieService) : MovieDataStore {

    override suspend fun getMovies(): List<Movie> {
        return movieService.discoverMovies().results
    }

}