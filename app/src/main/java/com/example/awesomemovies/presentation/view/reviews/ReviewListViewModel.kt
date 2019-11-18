package com.example.awesomemovies.presentation.view.reviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.Review
import com.example.awesomemovies.data.repository.movies.CloudMoviesDataStore
import com.example.awesomemovies.data.repository.reviews.CloudReviewDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReviewListViewModel(private val repository: CloudReviewDataStore) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val reviews: LiveData<List<Review>>
        get() = localReviews

    val reviewsCount: LiveData<Int>
        get() = localReviewsCount
    val isLoading: LiveData<Boolean>
        get() = localIsLoading
    val reviewsLoaded: LiveData<Boolean>
        get() = localReviewsLoaded


    private val localReviews = MutableLiveData<List<Review>>()
    private val localReviewsCount = MutableLiveData<Int>()
    private val localIsLoading = MutableLiveData<Boolean>()
    private val localReviewsLoaded = MutableLiveData<Boolean>()

    fun loadReviews(id: Int) {
        localIsLoading.postValue(true)
        launch(Dispatchers.IO) {
            try {
                val reviewsResponse = repository.getMovieReviews(id)
                val reviews = reviewsResponse.reviews
                var reviewsCount = reviewsResponse.totalResults
                localReviews.postValue(reviews)
                localReviewsCount.postValue(reviewsCount)
                localReviewsLoaded.postValue(true)
            } catch (error: Exception) {
                localReviewsLoaded.postValue(false)
            } finally {
                localIsLoading.postValue(false)
            }
        }
    }
}
