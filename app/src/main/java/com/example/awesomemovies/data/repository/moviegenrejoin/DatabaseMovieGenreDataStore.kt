package com.example.awesomemovies.data.repository.moviegenrejoin

import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.MovieGenreJoin

class DatabaseMovieGenreDataStore(private val movieGenreDao: MovieGenreJoinDao) {
    suspend fun saveMovieGenreDataStore(movieGenres: List<MovieGenreJoin>) {
        movieGenreDao.insert(*movieGenres.toTypedArray())
    }
    suspend fun getGenresForMovie(movieId: Int): List<Genre>{
        return movieGenreDao.getGenresForMovie(movieId).toList()
    }
}