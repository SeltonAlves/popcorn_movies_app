package com.mycompany.movies.view.viewholder

import android.graphics.Bitmap
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.ItemMainMoviesBinding

class FutureMoviesViewHolder(private val binding: ItemMainMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pair<String, Bitmap?>) {
        if (item.second != null) {
            binding.text.visibility = View.VISIBLE
            binding.text.text = item.first
            binding.image.setImageBitmap(item.second)
        }else{
            bindShimmer()
        }
    }

    fun bindShimmer() {
        binding.image.setImageDrawable(null)
        binding.text.text = ""
    }

    fun hideShimmer(){
        binding.shimmerFrame.hideShimmer()
    }

}