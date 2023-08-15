package com.mycompany.movies.view.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.util.OnClickListener

class MoviesPopularViewHolder(
    private val binding: RowItemMoviesPopularBinding,
    private val listener: OnClickListener
) :
    RecyclerView.ViewHolder(binding.root) {
    private val imageMovies = binding.imageMovies
    fun bind(item: Drawable?, position: Int, code: Int?) {
        if (item != null) {
            binding.imageMovies.setImageDrawable(item)
            binding.textPosition.visibility = View.VISIBLE
            binding.textPosition.text = position.toString()
            binding.shimmerLayout.shimmerDuration = 0
            binding.shimmerLayout.shimmerColor = 0

            binding.imageMovies.setOnClickListener {
                listener.onClick(code)
            }
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
