package com.example.awesomemovies.presentation.view.MovieDetail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.barriosartola.awesomeapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.barriosartola.awesomeapp.presentation.helper.gone
import com.barriosartola.awesomeapp.presentation.helper.visible
import com.barriosartola.awesomeapp.presentation.helper.visibleIf
import com.bumptech.glide.Glide
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.presentation.view.reviews.ReviewListActivity
import kotlinx.android.synthetic.main.movie_detail_fragment.*



class MovieDetailFragment : Fragment() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private var movieId = -1
    private val GotoReviewsRequestCode = 1002

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (arguments != null && arguments!!.getInt(ARG_MOVIE_ID, -1) != -1) {
            movieId = arguments!!.getInt(ARG_MOVIE_ID, -1)
        }

        setupFavoriteFunctionality()
        setupReviewsButtonFunctionality()

        movieDetailViewModel.loadMovie(movieId)
        movieDetailViewModel.isUpdating.observe(viewLifecycleOwner, Observer(this::updateStateChanged))
        movieDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
        movieDetailViewModel.movie.observe(viewLifecycleOwner, Observer(this::movieLoaded))
        movieDetailViewModel.savedFavorite.observe(viewLifecycleOwner, Observer(this::isFavorite))
    }

    private fun setupReviewsButtonFunctionality() {
        show_reviews_button.setOnClickListener {
            this.showReviews(movieId)
        }
    }

    private fun setupFavoriteFunctionality() {

        fav_image.setOnClickListener {
            if (fav_image.tag == "0") {
                // Add to favs
                movieDetailViewModel.saveFavorite()
            } else {
                //Remove from favs
                movieDetailViewModel.removeFavorite()
            }

        }
    }

    private fun isFavorite(favorite: Boolean){
        if(favorite){
            fav_image.setColorFilter(Color.RED)
            fav_image.setTag("1")
        }
        else{
            fav_image.setColorFilter(Color.GRAY)
            fav_image.setTag("0")
        }
    }

    private fun movieLoaded(movie: Movie?) {
        if (movie== null){
            no_movie.visible()
            movie_details.gone()
        }
        else{
            no_movie.gone()
            movie_details.visible()
            setUpMovieDetailLayout(movie)
        }
    }

    private fun setUpMovieDetailLayout(movie: Movie) {
        // Set movie image
        var imageView = movie_poster
        var ctx = imageView.context

        Glide.with(ctx)
            .load("https://image.tmdb.org/t/p/w185/" + movie.posterPath)
            .error(R.drawable.movie_image_place_holder)
            .into(imageView)

        movie_name.text = movie.title
        movie_year.text = movie.releaseDate.take(4)
        movie_rating.text = movie.voteAverage.toString()
        movie_resume.text = movie.overview

        // Get genres
        var genres = ""
        movie.genres.forEach {
            genres = genres + it.name + ", "
        }

        genres = genres.dropLast(2)
        genres_names.text = genres
    }

    private fun showReviews(id: Int) {
        activity?.let {

            var intent = Intent(it, ReviewListActivity::class.java)

            if (id != -1) {
                intent.putExtra(ReviewListActivity.ARG_MOVIE_ID, id)
            }

            startActivityForResult(intent, GotoReviewsRequestCode)
        }

    }

    fun loadingStateChanged(isLoading: Boolean){
        movie_details.visibleIf(!isLoading)
        progressBar.visibleIf(isLoading)
    }

    fun updateStateChanged(isLoading: Boolean){
        progressBar.visibleIf(isLoading)
    }

}
