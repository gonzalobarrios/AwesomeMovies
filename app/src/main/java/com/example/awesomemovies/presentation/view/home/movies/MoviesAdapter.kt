package com.barriosartola.awesomeapp.presentation.view.home.movies

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.barriosartola.awesomeapp.R
import com.bumptech.glide.Glide
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.movies_grid_item.view.*

class MoviesAdapter : BaseAdapter {
    var context: Context? = null
    var movies = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCategoryClicked: (id: Int) -> Unit = {}

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = movies[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var gridItemView = inflator.inflate(R.layout.movies_grid_item, null)
        gridItemView.movie_title.text = item.title

        // Ser movie image
        var imageView = gridItemView.movie_image
        var ctx = imageView.context

        Glide.with(ctx)
            .load("https://image.tmdb.org/t/p/w185/" + item.posterPath)
            .error(R.drawable.movie_image_place_holder)
            .into(imageView)

        gridItemView.vote_average.text = "★" + item.voteAverage.toString()
        gridItemView.movie_year.text = item.releaseDate.toString().take(4)

        gridItemView.setOnClickListener {
            onCategoryClicked(item.id)
        }

        return gridItemView
    }

}
