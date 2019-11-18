package com.example.awesomemovies.presentation.view.MovieDetail

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.barriosartola.awesomeapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import kotlinx.android.synthetic.main.movies_fragment.*
import kotlinx.android.synthetic.main.movies_grid_item.view.*


class MovieDetailFragment : Fragment() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private var movieId = -1

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

        // Get the movieId
        if (arguments != null && arguments!!.getInt(ARG_MOVIE_ID, -1) != -1) {
            movieId = arguments!!.getInt(ARG_MOVIE_ID, -1)
        }

        setupFavoriteFunctionality()

        movieDetailViewModel.loadMovie(movieId)
        movieDetailViewModel.movie.observe(viewLifecycleOwner, Observer(this::movieLoaded))
    }

    private fun setupFavoriteFunctionality() {

        fav_image.setOnClickListener {
            if (fav_image.tag == "0") {
                // Add to favs
                fav_image.setColorFilter(Color.RED)
                fav_image.setTag("1")
            } else {

                //Remove from favs
                fav_image.setColorFilter(Color.GRAY)
                fav_image.setTag("0")
            }

        }
    }

    private fun movieLoaded(movie: Movie) {

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
        movie.genres.forEach{
            genres = genres + it.name + ", "
        }

        genres = genres.dropLast(2)
        genres_names.text = genres
    }

}
