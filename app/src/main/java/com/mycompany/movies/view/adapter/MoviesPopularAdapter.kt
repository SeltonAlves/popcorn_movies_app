package com.mycompany.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.model.Movies
import com.mycompany.movies.view.viewholder.MoviesPopularVH

class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {
    private var listMovies: List<Movies> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularVH {
        val item =
            RowItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesPopularVH(item)
    }

    override fun getItemCount(): Int = listMovies.count()

    override fun onBindViewHolder(holder: MoviesPopularVH, position: Int) {
        holder.bind(listMovies[position])
    }
}