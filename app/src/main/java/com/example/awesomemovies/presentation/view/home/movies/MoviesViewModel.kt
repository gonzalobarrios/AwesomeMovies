package com.barriosartola.awesomeapp.presentation.view.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.data.repository.NotesSourceRepository
import com.example.awesomemovies.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesViewModel(private val repository: NotesSourceRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val notes: LiveData<List<Movie>>
        get() = localMovies
    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    private val localMovies = MutableLiveData<List<Movie>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadNotes() {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val movies = repository.getMovies()
                localMovies.postValue(movies)
            } catch (error: Exception) {

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }
}
