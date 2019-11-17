package com.example.awesomemovies.presentation.view.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.repository.movies.DatabaseMovieDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoriteModel (private val repository: DatabaseMovieDataStore) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    val movies: LiveData<List<Movie>>
        get() = localMovies
    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    private val localMovies = MutableLiveData<List<Movie>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadMovies() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val movies = repository.getFavoritesMovies()
                localMovies.postValue(movies)
            } catch (error: Exception) {
            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

}