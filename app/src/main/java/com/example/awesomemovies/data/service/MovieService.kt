package com.example.awesomemovies.data.service

import com.example.awesomemovies.data.service.response.MoviesResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    suspend fun discoverMovies(): MoviesResultResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query ("query") query: String): MoviesResultResponse
}