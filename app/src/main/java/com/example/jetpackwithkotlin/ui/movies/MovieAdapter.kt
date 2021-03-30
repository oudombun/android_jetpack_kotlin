    package com.example.jetpackwithkotlin.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackwithkotlin.data.Movie
import com.example.jetpackwithkotlin.databinding.ItemMovieBinding

class MovieAdapter: ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout= ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false )
        return MovieViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class MovieViewHolder(private val binding:ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movie){
            binding.apply {
                checkBoxCompleted.isChecked=movie.is_watched
                labelPriority.isVisible = movie.is_favorite
                textViewName.text = movie.title
            }
        }
    }

    class DiffCallback:DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem==newItem
    }
}