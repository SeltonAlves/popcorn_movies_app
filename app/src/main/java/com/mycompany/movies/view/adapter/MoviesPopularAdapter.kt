package com.mycompany.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.view.viewholder.MoviesPopularVH

class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularVH {
        val item =
            RowItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesPopularVH(item)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MoviesPopularVH, position: Int) {
    }
}