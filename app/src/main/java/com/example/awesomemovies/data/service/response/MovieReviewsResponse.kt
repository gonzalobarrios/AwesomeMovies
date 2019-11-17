package com.example.awesomemovies.data.service.response

import com.example.awesomemovies.data.model.Review
import com.google.gson.annotations.SerializedName

class MovieReviewsResponse
    (@SerializedName("results")val reviews: List<Review>,
     @SerializedName ("total_results") val totalResults: Int)
