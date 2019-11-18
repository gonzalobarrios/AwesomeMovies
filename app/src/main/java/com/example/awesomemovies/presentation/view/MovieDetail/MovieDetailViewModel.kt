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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailViewModel(private val repository: MoviesSourceRepository, private val repositoryGenre: DatabaseGenreDataStore) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val movie: LiveData<Movie>
        get() = localMovie

    val genres: LiveData<List<Genre>>
        get() = localGenres

    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    private val localMovie = MutableLiveData<Movie>()
    private val localGenres = MutableLiveData<List<Genre>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadMovie(id: Int) {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val movie = repository.getMovie(id)
                localMovie.postValue(movie)
            } catch (error: Exception) {
            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

//    fun loadGenres(genresIds: List<Int>) {
//        localIsLoading.postValue(true)
//        launch(Dispatchers.IO) {
//            try {
//                val genres = repositoryGenre.getAllGenresById(genresIds)
//                localGenres.postValue(genres)
//            } catch (error: Exception) {
//            } finally {
//                localIsLoading.postValue(false)
//            }
//        }
//    }
}
