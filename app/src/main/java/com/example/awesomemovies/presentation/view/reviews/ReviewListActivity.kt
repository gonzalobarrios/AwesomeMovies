package com.example.awesomemovies.presentation.view.reviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barriosartola.awesomeapp.R

class ReviewListActivity : AppCompatActivity() {

    companion object {
        public const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }

    private var movieId = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        if (intent.extras != null && intent.extras.getInt(ARG_MOVIE_ID, -1) != -1) {
            movieId = intent.extras.getInt(ARG_MOVIE_ID, -1)
        }

        if (savedInstanceState == null) {

            var frag = ReviewListFragment()

            if (movieId != -1) {
                var bundle = Bundle()
                bundle.putInt(ReviewListFragment.ARG_CATEGORY_ID, movieId)
                frag.arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commitNow()
        }
    }

}
