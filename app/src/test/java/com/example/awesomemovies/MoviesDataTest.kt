package com.example.awesomemovies

import android.content.Context
import com.barriosartola.awesomeapp.data.repository.notes.CloudNotesDataStore
import com.barriosartola.awesomeapp.data.repository.notes.DatabaseNotesDataStore
import com.barriosartola.awesomeapp.inject.movieGenreModule
import com.barriosartola.awesomeapp.mocks.NetworkingManagerMock
import com.barriosartola.awesomeapp.mocks.NotesDataStoreFactoryMock
import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.dao.MovieDao
import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.repository.GenreSourceDataRepository
import com.example.awesomemovies.data.repository.genres.DatabaseGenreDataStore
import com.example.awesomemovies.data.repository.genres.GenreDataStore
import com.example.awesomemovies.data.repository.moviegenrejoin.DatabaseMovieGenreDataStore
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import com.example.awesomemovies.data.repository.movies.DatabaseMovieDataStore
import com.example.awesomemovies.data.repository.movies.MoviesDataStoreFactory
import com.example.awesomemovies.data.service.MovieService
import com.example.awesomemovies.mocks.GenreDaoMock
import com.example.awesomemovies.mocks.MovieDaoMock
import com.example.awesomemovies.mocks.MovieGenreDaoMock
import com.example.awesomemovies.mocks.MoviesServiceMock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext

@RunWith(MockitoJUnitRunner::class)
class MoviesDataTes: CoroutineScope  {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    @Mock
    private lateinit var mockContext: Context


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    lateinit var networkingManagerMock: NetworkingManagerMock
    lateinit var movieDao: MovieDao
    lateinit var movieService: MovieService
    lateinit var movieGenreDataStore: DatabaseMovieGenreDataStore
    lateinit var genreDataStore: DatabaseGenreDataStore
    lateinit var genreDao: GenreDao
    lateinit var genreSourceDataRepository: GenreSourceDataRepository
    lateinit var movieGenreJoinDao: MovieGenreJoinDao
    lateinit var moviesDataStoreFactory: MoviesDataStoreFactory

    @Before
    fun beforeEachTest(){
        Dispatchers.setMain(mainThreadSurrogate)

        movieService = MoviesServiceMock()
        networkingManagerMock = NetworkingManagerMock(mockContext)
        movieDao = MovieDaoMock()
        genreDao = GenreDaoMock()
        genreDataStore = DatabaseGenreDataStore(genreDao)
        genreSourceDataRepository = GenreSourceDataRepository(genreDataStore)
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

    @Test
    fun genreSourceGetMockDataCorrectly(){ runBlocking {
        launch(Dispatchers.Main) {
            val genre = genreSourceDataRepository.getGenre(7)
            assert(genre.id == 7)
            assert(genre.name == "El genre papa")        }
    }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}