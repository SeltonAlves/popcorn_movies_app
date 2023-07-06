package com.mycompany.movies.view.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.view.viewholder.MoviesPopularVH

class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {
    private var list = listOf<Bitmap?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularVH {
        val item =
            RowItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesPopularVH(item)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: MoviesPopularVH, position: Int) {
        holder.bind(list[position], position +1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(item: List<Bitmap?>) {
        list = item
        notifyDataSetChanged()
    }
}