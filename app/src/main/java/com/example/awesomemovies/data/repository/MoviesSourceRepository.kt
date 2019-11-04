package com.example.awesomemovies.data.repository

import com.example.awesomemovies.data.model.Movie

interface MoviesSourceRepository {

    suspend fun getNotes(): List<Movie>
}