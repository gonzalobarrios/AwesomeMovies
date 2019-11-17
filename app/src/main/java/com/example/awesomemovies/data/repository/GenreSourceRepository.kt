package com.example.awesomemovies.data.repository

import com.example.awesomemovies.data.model.Genre

interface GenreSourceRepository {

    suspend fun getGenres(): List<Genre>

    suspend fun getGenre(id: Int): Genre
}