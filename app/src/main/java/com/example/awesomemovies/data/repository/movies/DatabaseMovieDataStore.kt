package com.example.awesomemovies.data.repository.movies

import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.MovieGenreJoin
import com.example.awesomemovies.data.repository.moviegenrejoin.DatabaseMovieGenreDataStore

class DatabaseMovieDataStore (private val movieDao: MovieDao, private val movieGenreDataStore: DatabaseMovieGenreDataStore) : MovieDataStore {

    suspend fun getFavoritesMovies(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun getMovie(id: Int): Movie{
        var movie = movieDao.getMovie(id)
        var genres = listOf<Genre>()
        if(movie != null){
            genres = movieGenreDataStore.getGenresForMovie(movie.id)
        }
        return movie.copy(genres = genres)
    }

    suspend fun deleteMovie(movie: Movie){
        movieGenreDataStore.deleteGenreRelationForMovie(movie.id)
        movieDao.delete(movie)
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