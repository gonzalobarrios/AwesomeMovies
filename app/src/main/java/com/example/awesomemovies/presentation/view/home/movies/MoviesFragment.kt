package com.barriosartola.awesomeapp.presentation.view.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.presentation.helper.visibleIf
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.movies_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {
    private lateinit var adapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapter = MoviesAdapter(context!!)

        return inflater.inflate(R.layout.movies_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init adapter
        movies_grid.adapter = adapter

        moviesViewModel.loadMovies()
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer(this::moviesLoaded))
        moviesViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
    }

    private fun moviesLoaded(movies: List<Movie>) {
        adapter.movies = movies
    }

    private fun loadingStateChanged(isLoading: Boolean) {
//        progressBar.visibleIf(isLoading)
//        movies_grid.visibleIf(!isLoading)
    }
}
