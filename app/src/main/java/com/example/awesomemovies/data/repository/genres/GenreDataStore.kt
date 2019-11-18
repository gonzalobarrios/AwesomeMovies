package com.example.awesomemovies.data.repository.genres

import com.example.awesomemovies.data.model.Genre

interface GenreDataStore {
    suspend fun getGenre(id: Int): Genre
    suspend fun getGenres(): List<Genre>
    suspend fun getAllGenresById(genresIds: List<Int>): List<Genre>
}