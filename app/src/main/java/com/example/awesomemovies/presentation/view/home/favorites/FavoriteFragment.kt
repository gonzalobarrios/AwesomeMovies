package com.barriosartola.awesomeapp.presentation.view.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.presentation.view.home.movies.MoviesAdapter
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.presentation.view.MovieDetail.MovieDetailActivity
import com.example.awesomemovies.presentation.view.home.favorites.FavoriteModel
import kotlinx.android.synthetic.main.movies_grid.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private val favoriteViewModel: FavoriteModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = MoviesAdapter(context!!)
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init adapter
        adapter = MoviesAdapter(context!!)
        movies_grid.adapter = adapter
        adapter.onCategoryClicked = this::onMovieClicked

        favoriteViewModel.loadMovies()
        favoriteViewModel.movies.observe(viewLifecycleOwner, Observer (this::moviesLoaded))
        favoriteViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
    }

    private fun moviesLoaded(movies: List<Movie>) {
        adapter.movies = movies
    }

    private fun loadingStateChanged(isLoading: Boolean) {
    }

    private fun onMovieClicked(id: Int) {

        activity?.let {

            var intent = Intent(it, MovieDetailActivity::class.java)

            if (id != -1) {
                intent.putExtra(MovieDetailActivity.ARG_MOVIE_ID, id)
            }

            startActivityForResult(intent, 1001)
        }
    }

}
