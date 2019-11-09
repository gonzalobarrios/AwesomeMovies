package com.example.awesomemovies.data.service

import com.barriosartola.awesomeapp.data.model.Note
import com.example.awesomemovies.data.model.Movie
import retrofit2.http.GET

interface MovieService {
    @GET("discover/movie-discover")
    suspend fun discoverMovies(): List<Movie>
}