package com.example.awesomemovies.presentation.view.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barriosartola.awesomeapp.R
import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.model.Review
import kotlinx.android.synthetic.main.review_item_view.view.*
import kotlinx.android.synthetic.main.review_list_fragment.view.*
import kotlinx.android.synthetic.main.view_note.view.*
import org.threeten.bp.format.DateTimeFormatter

class ReviewListAdapter: RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    var reviews = listOf<Review>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item_view, parent, false)
            .let { view -> ReviewViewHolder(view) }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review) {
            itemView.author_name.text = review.author
            itemView.comment.text = review.content
        }
    }
}