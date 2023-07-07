package com.mycompany.movies.view.viewholder

import android.graphics.Bitmap
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding

class MoviesPopularVH(private val binding: RowItemMoviesPopularBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Bitmap?, position: Int) {
        if (item != null) {
            binding.imageMovies.setImageBitmap(item)
            binding.textPosition.text = position.toString()
        } else {
            bindShimmer()
        }
    }

    fun bindShimmer() {
        binding.imageMovies.setImageDrawable(null)
        binding.textPosition.text = ""
    }

    fun enable() {
        binding.shimmerFrame.hideShimmer()
    }
}
