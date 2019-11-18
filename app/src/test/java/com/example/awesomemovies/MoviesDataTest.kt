package com.example.awesomemovies

import android.content.Context
import com.barriosartola.awesomeapp.data.repository.notes.CloudNotesDataStore
import com.barriosartola.awesomeapp.data.repository.notes.DatabaseNotesDataStore
import com.barriosartola.awesomeapp.inject.movieGenreModule
import com.barriosartola.awesomeapp.mocks.NetworkingManagerMock
import com.barriosartola.awesomeapp.mocks.NotesDataStoreFactoryMock
import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.repository.moviegenrejoin.DatabaseMovieGenreDataStore
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import com.example.awesomemovies.data.repository.movies.DatabaseMovieDataStore
import com.example.awesomemovies.data.repository.movies.MoviesDataStoreFactory
import com.example.awesomemovies.data.service.MovieService
import com.example.awesomemovies.mocks.MovieDaoMock
import com.example.awesomemovies.mocks.MovieGenreDaoMock
import com.example.awesomemovies.mocks.MoviesServiceMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesDataTest {
    @Mock
    private lateinit var mockContext: Context

    lateinit var networkingManagerMock: NetworkingManagerMock
    lateinit var movieDao: MovieDao
    lateinit var movieService: MovieService
    lateinit var movieGenreDataStore: DatabaseMovieGenreDataStore
    lateinit var movieGenreJoinDao: MovieGenreJoinDao
    lateinit var moviesDataStoreFactory: MoviesDataStoreFactory

    @Before
    fun beforeEachTest(){
        movieService = MoviesServiceMock()
        networkingManagerMock = NetworkingManagerMock(mockContext)
        movieDao = MovieDaoMock()
        movieGenreJoinDao = MovieGenreDaoMock()
        movieGenreDataStore = DatabaseMovieGenreDataStore(movieGenreJoinDao)
        moviesDataStoreFactory = MoviesDataStoreFactory(movieService, movieDao, networkingManagerMock, movieGenreDataStore )
    }

    @Test
    fun testCloudSourceDataRetrieving() {
        networkingManagerMock.networkingAvailable = true

        assert(moviesDataStoreFactory.moviesDataStoreFactory is CloudMoviesDataStore)
    }

    @Test
    fun testDatabaseSourceDataRetrieving() {
        networkingManagerMock.networkingAvailable = false

        assert(moviesDataStoreFactory.moviesDataStoreFactory is DatabaseMovieDataStore)
    }

    @Test
    fun testNetworking() {
        val networkingManager = NetworkingManagerMock(mockContext)
        assert(networkingManager.isNetworkOnline())
    }
}