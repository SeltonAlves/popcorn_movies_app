package com.mycompany.movies.view.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.ItemMainMoviesBinding

class FutureMoviesViewHolder(private val binding: ItemMainMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

   private val img = binding.image

    fun bind( title: String,img: Drawable?) {
        if (img != null) {
            binding.text.visibility = View.VISIBLE
            binding.text.text = title
            binding.image.setImageDrawable(img)
            binding.shimmerLayout.shimmerDuration = 0
            binding.shimmerLayout.shimmerColor = 0
        }else{
            bindShimmer()
        }
    }

    fun bindShimmer() {
        binding.image.setImageDrawable(null)
        binding.text.text = ""
    }

    fun returnImg (): ImageView {
        return img
    }

}