package com.mycompany.movies.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowSearchBinding
import com.mycompany.movies.model.Result

class SearchViewHolder(private val binding: RowSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : Result){
        binding.textNameMovies.text = item.title
    }
}
