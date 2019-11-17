package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.MovieGenreJoin

class DatabaseMovieDataStore (private val movieDao: MovieDao, private val movieGenreDataStore: DatabaseMovieGenreDataStore) : MovieDataStore {

    override suspend fun getMovies(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return listOf<Movie>()
    }

    suspend fun saveMovie(movie: Movie){
        movieDao.insert(movie)
        val genres = movie.genres
        val movieGenreMutableCustList = mutableListOf<MovieGenreJoin>()
        if(genres.isNotEmpty()){
            genres.forEach{
                movieGenreMutableCustList.add(
                    MovieGenreJoin(
                        movie.id,
                        it.id
                    )
                )
            }
            movieGenreDataStore.saveMovieGenreDataStore(movieGenreMutableCustList)
        }
    }
}