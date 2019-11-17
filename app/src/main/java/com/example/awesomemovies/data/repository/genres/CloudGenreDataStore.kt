package com.example.awesomemovies.data.repository.genres

import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.service.GenreService

class CloudGenreDataStore (private val genreService: GenreService, private val genreDao: GenreDao){

    suspend fun getGenres(): List<Genre> {
        val genres = genreService.getGenres().genres
        genreDao.insertAll(*genres.toTypedArray())
        return genres
    }

}