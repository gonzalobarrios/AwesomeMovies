package com.example.awesomemovies.data.service

import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.service.response.GenresResultResponse
import com.example.awesomemovies.data.service.response.MoviesResultResponse
import retrofit2.http.GET

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenres(): GenresResultResponse
}