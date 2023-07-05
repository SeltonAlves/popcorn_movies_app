package com.mycompany.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.model.Result
import com.mycompany.movies.view.viewholder.MoviesPopularVH

class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {
    private var list : List<Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularVH {
        val item =
            RowItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesPopularVH(item)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: MoviesPopularVH, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(item: List<Result>) {
        list = item
    }
}