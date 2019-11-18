package com.example.awesomemovies.presentation.view.reviews

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.barriosartola.awesomeapp.R

class ReviewListFragment : Fragment() {
    companion object {
        public const val ARG_CATEGORY_ID = "ARG_CATEGORY_ID"
        fun newInstance() = ReviewListFragment()

    }
    private lateinit var viewModel: ReviewListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReviewListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
