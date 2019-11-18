package com.barriosartola.awesomeapp.presentation.view.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.repository.MoviesSourceRepository
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesViewModel(private val repository: CloudMoviesDataStore) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val movies: LiveData<List<Movie>>
        get() = localMovies
    val isLoading: LiveData<Boolean>
        get() = localIsLoading
    val moviesLoaded: LiveData<Boolean>
        get() = localMoviesLoaded



    private val localMovies = MutableLiveData<List<Movie>>()
    private val localIsLoading = MutableLiveData<Boolean>()
    private val localMoviesLoaded = MutableLiveData<Boolean>()


    fun loadMovies() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val movies = repository.getMovies()
                localMovies.postValue(movies)
                localMoviesLoaded.postValue(true)
            } catch (error: Exception) {
                localMoviesLoaded.postValue(false)
            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

    fun searchMovies(text: String) {
        launch(Dispatchers.IO) {
            try {
                val movies = repository.searchMovies(text)
                if (!movies.isEmpty()) {
                    localMovies.postValue(movies)
                    localMoviesLoaded.postValue(true)
                }

            } catch (error: Exception) {
                localMoviesLoaded.postValue(false)

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

    fun filterMovies(voteAverageMin: Int, voteAverageMax: Int) {
        launch(Dispatchers.IO) {
            try {
                val movies = repository.discoverMovies(voteAverageMin, voteAverageMax)
                if (!movies.isEmpty()) {
                    localMovies.postValue(movies)
                    localMoviesLoaded.postValue(true)

                }

            } catch (error: Exception) {
                localMoviesLoaded.postValue(false)

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }
}
