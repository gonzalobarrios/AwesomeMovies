package com.example.awesomemovies.data.repository.movies
import com.example.awesomemovies.data.model.Movie

interface MovieDataStore {
    suspend fun getMovies(): List<Movie>

}