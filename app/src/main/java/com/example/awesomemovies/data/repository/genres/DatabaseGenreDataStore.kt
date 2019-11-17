package com.example.awesomemovies.data.repository.genres

import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.model.Genre

class DatabaseGenreDataStore(private val genreDao: GenreDao) {

    suspend fun getGenre(id: Int): Genre {
        return genreDao.get(id)
    }

    suspend fun getGenres(): List<Genre> {
        return genreDao.getAll()
    }

}