package com.example.awesomemovies.data.service.response

import com.example.awesomemovies.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResultResponse (@SerializedName("page")val page: Int,
                                 @SerializedName("total_results")val total_results: Int,
                                 @SerializedName("total_pages")val total_pages: Int,
                                 @SerializedName("results")val results: List<Movie>)