package com.mycompany.movies.view.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowSearchBinding

class SearchViewHolder(private val binding: RowSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    val img = binding.imagePoster

    fun bind(img: Drawable?, title: String, popularity: Double, date: String){
        if (img!= null){
            binding.imagePoster.setImageDrawable(img)
            binding.textNameMovies.text = title
            binding.textStart.text = popularity.toString()
            binding.textCalendar.text = date
            binding.shimmerLayout.shimmerDuration = 0
            binding.shimmerLayout.shimmerColor = 0
        }

    }

    fun bindShimmer(){
        binding.imageCalendar.setImageDrawable(null)
        binding.textNameMovies.text = ""
    }
}
