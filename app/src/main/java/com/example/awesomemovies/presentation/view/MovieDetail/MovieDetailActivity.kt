package com.example.awesomemovies.presentation.view.MovieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barriosartola.awesomeapp.R

class MovieDetailActivity : AppCompatActivity() {

    private var movieId = -1

    companion object {
        private const val MovieDetailFragmentTag = "MovieDetailFragmentTag"
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        if (intent.extras != null && intent.extras.getInt(ARG_MOVIE_ID, -1) != -1) {
            movieId = intent.extras.getInt(ARG_MOVIE_ID, -1)
        }

        if (savedInstanceState == null) {

            var frag = MovieDetailFragment()

            if (movieId != -1) {
                var bundle = Bundle()
                bundle.putInt(MovieDetailFragment.ARG_MOVIE_ID, movieId)
                frag.arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commitNow()
        }

    }

}
