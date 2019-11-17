package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.model.MovieGenreJoin

class DatabaseMovieGenreDataStore(private val movieGenreDao: MovieGenreJoinDao) {
    suspend fun saveMovieGenreDataStore(movieGenres: List<MovieGenreJoin>) {
        movieGenreDao.insert(*movieGenres.toTypedArray())
    }
}