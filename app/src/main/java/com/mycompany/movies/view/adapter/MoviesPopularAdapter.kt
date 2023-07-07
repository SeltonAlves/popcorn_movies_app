package com.mycompany.movies.view.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.view.viewholder.MoviesPopularVH

 class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {
     private var list = listOf<Bitmap?>()
     private var showShimmer = true


     private companion object {
         private const val SHIMMER_ITEM_COUNT = 10
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularVH {
         val item =
             RowItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return MoviesPopularVH(item)
     }

     override fun getItemCount(): Int {
         return if (showShimmer) {
             SHIMMER_ITEM_COUNT
         } else {
             list.count()
         }
     }

     override fun onBindViewHolder(holder: MoviesPopularVH, position: Int) {
         if (showShimmer) {
             holder.bindShimmer()
         } else {
             holder.bind(list[position], position + 1)
             holder.enable()
         }
     }

     @SuppressLint("NotifyDataSetChanged")
     fun setItem(item: List<Bitmap?>) {
         showShimmer = false
         list = item
         notifyDataSetChanged()
     }

     @SuppressLint("NotifyDataSetChanged")
     fun startShimmerAnimation() {
         showShimmer = true
         notifyDataSetChanged()
     }

     @SuppressLint("NotifyDataSetChanged")
     fun stopShimmerAnimation() {
         showShimmer = false
         notifyDataSetChanged()
     }
 }
