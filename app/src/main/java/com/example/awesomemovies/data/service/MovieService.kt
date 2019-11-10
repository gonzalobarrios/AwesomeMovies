package com.example.awesomemovies.data.service

import com.barriosartola.awesomeapp.data.model.Note
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.response.DiscoverResponse
import retrofit2.http.GET

interface MovieService {
    @GET("discover/movie")
    suspend fun discoverMovies(): DiscoverResponse
}