package com.mycompany.movies.view.viewholder

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.caverock.androidsvg.SVG
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding

class MoviesPopularVH(private val binding: RowItemMoviesPopularBinding) :
    RecyclerView.ViewHolder(binding.root) {
   private val imageMovies = binding.imageMovies
    fun bind(item: Drawable?, position: Int) {
        if (item != null ) {
            binding.imageMovies.setImageDrawable(item)
            binding.textPosition.text = position.toString()
            binding.shimmerLayout.shimmerDuration = 0
            binding.shimmerLayout.shimmerColor = 0
        } else {
            bindShimmer()
        }
    }

    fun bindShimmer() {
        binding.imageMovies.setImageDrawable(null)
        binding.textPosition.text = ""
    }

    fun returnImg (): ImageView {
        return imageMovies
    }
}
