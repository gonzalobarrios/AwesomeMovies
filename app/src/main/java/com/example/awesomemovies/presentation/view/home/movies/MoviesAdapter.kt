package com.barriosartola.awesomeapp.presentation.view.home.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.data.model.Note
import com.example.awesomemovies.data.model.Movie
import kotlinx.android.synthetic.main.view_note.view.*
import org.threeten.bp.format.DateTimeFormatter

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    var movies = listOf<Movie>()
        set(value) {
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.view_note, parent, false)
            .let { view -> MovieViewHolder(view) }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieViewHolder(itemView: View) : GridView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.contentTextView.text = movie.content
            itemView.dateTextView.text = DateTimeFormatter
                .ofPattern("dd MMMM, yyyy")
                .format(movie.createdAt)
        }
    }
}
