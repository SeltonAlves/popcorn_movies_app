package com.mycompany.movies.view.viewholder

import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.R
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding

class MoviesPopularVH(private val binding: RowItemMoviesPopularBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Bitmap?, position: Int) {
        if (item != null) {
            binding.imageMovies.setImageBitmap(item)
            binding.textPosition.text = position.toString()
        } else {
            binding.imageMovies.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.image_not_available
                )
            )
        }
    }

}
