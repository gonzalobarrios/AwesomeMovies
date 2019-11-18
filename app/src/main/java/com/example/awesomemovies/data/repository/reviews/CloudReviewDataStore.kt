package com.example.awesomemovies.data.repository.reviews

import com.example.awesomemovies.data.service.ReviewService
import com.example.awesomemovies.data.service.response.MovieReviewsResponse

class CloudReviewDataStore(private val reviewService: ReviewService){
    suspend fun getMovieReviews(movieId: Int): MovieReviewsResponse {
        return reviewService.getMovieReviews(movieId)
    }
}