package com.mycompany.movies.view.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.ItemMainMoviesBinding
import com.mycompany.movies.util.OnClickListener

class MainMoviesViewHolder(private val binding: ItemMainMoviesBinding, private val listener: OnClickListener) :
    RecyclerView.ViewHolder(binding.root) {

   private val img = binding.image

    fun bind( title: String?,img: Drawable?, code: Int?) {
        if (img != null) {
            binding.text.visibility = View.VISIBLE
            binding.text.text = title
            binding.image.setImageDrawable(img)
            binding.shimmerLayout.shimmerDuration = 0
            binding.shimmerLayout.shimmerColor = 0

            binding.text.setOnClickListener {
                listener.onClick(code)
            }
            binding.image.setOnClickListener {
                listener.onClick(code)
            }
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