package com.example.awesomemovies.presentation.view.reviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.presentation.helper.visibleIf
import com.example.awesomemovies.data.model.Review
import com.example.awesomemovies.presentation.view.MovieDetail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.recyclerView
import kotlinx.android.synthetic.main.movie_detail_fragment.progressBar
import kotlinx.android.synthetic.main.review_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewListFragment : Fragment() {

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

    }

    private val adapter = ReviewListAdapter()
    private val reviewListViewModel: ReviewListViewModel by viewModel()
    private var movieId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@ReviewListFragment.adapter
        }

        if (arguments != null && arguments!!.getInt(ARG_MOVIE_ID, -1) != -1) {
            movieId = arguments!!.getInt(ARG_MOVIE_ID, -1)
        }

        reviewListViewModel.loadReviews(movieId)
        reviewListViewModel.isLoading.observe(viewLifecycleOwner, Observer(this::loadingStateChanged))
        reviewListViewModel.reviews.observe(viewLifecycleOwner, Observer(this::reviewsLoaded))
        reviewListViewModel.reviewsCount.observe(viewLifecycleOwner, Observer(this::reviewsCountLoaded))
    }

    private fun reviewsLoaded(reviews: List<Review>) {
        adapter.reviews = reviews
    }

    private fun reviewsCountLoaded(count: Int) {
        if (count > 0) {
            no_reviews_tv.isVisible = false
            reviews_count.text = "Reviews: " + count
        } else {
            no_reviews_tv.isVisible = true
            reviews_count.isVisible = false
        }

    }

    fun loadingStateChanged(isLoading: Boolean){
        recyclerView.visibleIf(!isLoading)
        progressBar.visibleIf(isLoading)
    }

}
