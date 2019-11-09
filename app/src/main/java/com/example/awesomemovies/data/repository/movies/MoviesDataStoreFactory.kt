package com.example.awesomemovies.data.repository.movies

import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager
import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.service.MovieService

@Suppress("UNUSED_PARAMETER")
open class MoviesDataStoreFactory(
    var service: MovieService,
    var dao: MovieDao,
    var networkingManager: NetworkingManager
) {

    open var moviesDataStoreFactory: MovieDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (networkingManager.isNetworkOnline()) {
        CloudMoviesDataStore(service)
    } else {
        DatabaseMovieDataStore(dao)
    }
}