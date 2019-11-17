package com.example.awesomemovies.data.service.response

import com.example.awesomemovies.data.model.Genre
import com.google.gson.annotations.SerializedName

class GenresResultResponse (@SerializedName("genres")val genres: List<Genre>)