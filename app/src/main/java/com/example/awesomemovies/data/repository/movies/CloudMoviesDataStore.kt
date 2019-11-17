package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.MovieService

class CloudMoviesDataStore (private val movieService: MovieService, private val movieDao: MovieDao) : MovieDataStore {

    suspend fun discoverMovies(voteAvarageMin: Int? = null, voteAvarageMax: Int? = null): List<Movie> {
        val movies = movieService.discoverMovies(voteAvarageMin, voteAvarageMax).results
        /*if(movies.isNotEmpty()){
            saveMovies(movies)
        }*/
        return movies
    }

    override suspend fun getMovies(): List<Movie> {
        val movies = movieService.discoverMovies(vote_average_min = null, vote_average_max = null).results
        /*if(movies.isNotEmpty()){
            saveMovies(movies)
        }*/
        return movies
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        val movies = movieService.searchMovies(query).results
//        if(movies.isNotEmpty()){
//            saveMovies(movies)
//        }
        return movies
    }

    private suspend fun saveMovies(movies: List<Movie>){
        movieDao.controlledInsert(*movies.toTypedArray())
    }
}