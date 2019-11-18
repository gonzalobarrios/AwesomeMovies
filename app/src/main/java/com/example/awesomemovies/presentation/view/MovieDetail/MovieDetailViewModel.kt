package com.example.awesomemovies.presentation.view.MovieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.dao.MovieGenreJoinDao
import com.example.awesomemovies.data.model.Genre
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.repository.MoviesSourceRepository
import com.example.awesomemovies.data.repository.genres.DatabaseGenreDataStore
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import com.example.awesomemovies.data.repository.movies.DatabaseMovieDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailViewModel(private val repository: MoviesSourceRepository, private val movieDataStore: DatabaseMovieDataStore) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val movie: LiveData<Movie>
        get() = localMovie

    val genres: LiveData<List<Genre>>
        get() = localGenres

    val savedFavorite: LiveData<Boolean>
        get() = localSavedFavorite

    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    val isUpdating: LiveData<Boolean>
        get() = localIsUpdating

    private val localIsUpdating = MutableLiveData<Boolean>()
    private val localMovie = MutableLiveData<Movie>()
    private val localSavedFavorite = MutableLiveData<Boolean>()
    private val localGenres = MutableLiveData<List<Genre>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadMovie(id: Int) {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val movie = repository.getMovie(id)
                localMovie.postValue(movie)
                isFavorite()
            } catch (error: Exception) {
                localMovie.postValue(null)

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

    fun saveFavorite(){
        localIsUpdating.postValue(true)

        launch(Dispatchers.IO) {
            try {
                if(movie.value != null) {
                    movieDataStore.saveMovie(movie.value!!)
                    localSavedFavorite.postValue(true)
                }
            } catch (error: Exception){
                val a = error
                localSavedFavorite.postValue(false)
            }finally {
                localIsUpdating.postValue(false)
            }
        }
    }

    fun removeFavorite(){
        localIsUpdating.postValue(true)

        launch(Dispatchers.IO) {
            try {
                if(movie.value != null) {
                    movieDataStore.deleteMovie(movie.value!!)
                    localSavedFavorite.postValue(false)
                }
            } catch (error: Exception){
                val a = error
            }
            finally {
                localIsUpdating.postValue(false)
            }
        }
    }


    private fun isFavorite(){
        launch(Dispatchers.IO) {
            try {
                if(movie.value != null) {
                    if(movieDataStore.getMovie(movie.value!!.id) != null){
                        localSavedFavorite.postValue(true)
                    }
                    else {
                        localSavedFavorite.postValue(false)
                    }
                }
            } catch (error: Exception){
                val a = error
                localSavedFavorite.postValue(false)
            }
        }
    }
}
