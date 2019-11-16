package com.barriosartola.awesomeapp.presentation.view.home.movies

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.barriosartola.awesomeapp.R
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.movies_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var starButtons: Array<ImageButton>


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
        adapter = MoviesAdapter(context!!)
        movies_grid.adapter = adapter

        setupSearchBar()
        setStarActions()
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

    private fun setupSearchBar() {
        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                moviesViewModel.searchMovies(newText, 0)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                moviesViewModel.searchMovies(query, 0)
                search_bar.clearFocus();
                return false
            }
        })
    }

    private fun setStarActions() {
        starButtons = arrayOf(one_star, two_star, three_star,four_star,five_star)
        starButtons.forEach { star ->
        star.setOnClickListener {
            val tag = (star.tag) as String
            when(tag) {
                "0" -> starAction(0)
                "1" -> starAction(1)
                "2" -> starAction(2)
                "3" -> starAction(3)
                "4" -> starAction(4)
                "-1" -> starAction(-1)
                else -> println("Number too high")
            }

            }
        }

    }

    private  fun starAction(tag: Int) {
        if (tag == -1) {
            val untilValue = starButtons.size - 1
            for (i in 0..untilValue ) {
                starButtons[i].tag = "" + i
                starButtons[i].setColorFilter(Color.GRAY)
            }
            moviesViewModel.loadMovies()
        } else {
            for (i in 0..tag) {
                starButtons[i].setColorFilter(Color.YELLOW)
                starButtons[i].tag = "-1"
            }

            moviesViewModel.searchMovies("Alice", tag + 1)
        }


    }
}
