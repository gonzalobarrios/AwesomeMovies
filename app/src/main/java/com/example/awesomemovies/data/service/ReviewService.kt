package com.example.awesomemovies.data.service

import com.example.awesomemovies.data.service.response.MovieReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {
    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(@Path("id") id: Int): MovieReviewsResponse
}