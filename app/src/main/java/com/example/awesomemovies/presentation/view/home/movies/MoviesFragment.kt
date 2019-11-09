package com.barriosartola.awesomeapp.presentation.view.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.data.model.Note
import com.barriosartola.awesomeapp.presentation.helper.visibleIf
import com.barriosartola.awesomeapp.presentation.view.home.notes.MoviesAdapter
import com.barriosartola.awesomeapp.presentation.view.home.notes.MoviesViewModel
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {
    private val adapter = MoviesAdapter()
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_notes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@MoviesFragment.adapter
        }

        moviesViewModel.loadNotes()
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer(this::moviesLoaded))
        moviesViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
    }

    private fun notesLoaded(movies: List<Movie>) {
        adapter.movies = movies
    }

    private fun loadingStateChanged(isLoading: Boolean) {
        progressBar.visibleIf(isLoading)
        recyclerView.visibleIf(!isLoading)
    }
}
