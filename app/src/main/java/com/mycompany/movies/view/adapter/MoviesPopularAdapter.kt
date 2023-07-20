package com.mycompany.movies.view.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.mycompany.movies.databinding.RowItemMoviesPopularBinding
import com.mycompany.movies.util.Constraints
import com.mycompany.movies.view.viewholder.MoviesPopularVH

class MoviesPopularAdapter : RecyclerView.Adapter<MoviesPopularVH>() {
    private var list = listOf<String>()
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

    override fun onBindViewHolder(
        holder: MoviesPopularVH,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (showShimmer) {
            holder.bindShimmer()
        } else {
            val imgSuffix = list[position]
            val imgUrl = Constraints.URL_BASE_IMG + imgSuffix.dropLast(3) + "svg"

            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)

            val glideUrl = GlideUrl(
                imgUrl,
                LazyHeaders.Builder()
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .build()
            )


            Glide.with(holder.itemView.context).load(glideUrl).apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(object : DrawableImageViewTarget(holder.returnImg()) {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        holder.bind(resource, position + 1)

                    }
                })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(item: List<String>) {
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

