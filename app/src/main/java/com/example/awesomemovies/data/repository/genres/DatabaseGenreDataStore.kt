package com.example.awesomemovies.data.repository.genres

import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.model.Genre

class DatabaseGenreDataStore(private val genreDao: GenreDao): GenreDataStore {

    override suspend fun getGenre(id: Int): Genre {
        return genreDao.get(id)
    }

    override suspend fun getGenres(): List<Genre> {
        return genreDao.getAll()
    }

    override suspend fun getAllGenresById(genresIds: List<Int>): List<Genre> {
        return genreDao.getAllGenresById(genresIds)
    }

}