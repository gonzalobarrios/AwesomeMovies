package com.example.awesomemovies.data.repository.genres

import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager
import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.repository.moviegenrejoin.DatabaseMovieGenreDataStore
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import com.example.awesomemovies.data.repository.movies.DatabaseMovieDataStore
import com.example.awesomemovies.data.repository.movies.MovieDataStore
import com.example.awesomemovies.data.service.GenreService
import com.example.awesomemovies.data.service.MovieService


@Suppress("UNUSED_PARAMETER")
open class GenreDataStoreFactory(
    var service: GenreService,
    var dao: GenreDao,
    var networkingManager: NetworkingManager) {

    open var genresDataStoreFactory: GenreDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (networkingManager.isNetworkOnline()) {
        CloudGenreDataStore(service, dao)
    } else {
        DatabaseGenreDataStore(dao)
    }
}