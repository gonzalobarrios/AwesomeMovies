package com.example.awesomemovies.data.repository

import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.repository.genres.DatabaseGenreDataStore

class GenreSourceDataRepository(var dataStore: DatabaseGenreDataStore): GenreSourceRepository{

    override suspend fun getGenres(): List<Genre> {
       return dataStore.getGenres()
}

    override suspend fun getGenre(id: Int): Genre {
        return dataStore.getGenre(id)
    }

}