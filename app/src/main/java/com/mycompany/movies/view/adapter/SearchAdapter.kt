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
import com.mycompany.movies.databinding.RowSearchBinding
import com.mycompany.movies.model.Result
import com.mycompany.movies.view.viewholder.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var isLoading: Boolean = true
    private var list = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item = RowSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(item)
    }

    override fun getItemCount(): Int {
        return if (isLoading) 5 else list.count()
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (isLoading) {
            holder.bindShimmer()
        } else {
            val imgUrl =
                "https://image.tmdb.org/t/p/original" + list[position].poster_path.dropLast(3) + "svg"

            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)

            val glideUrl = GlideUrl(
                imgUrl,
                LazyHeaders.Builder()
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .build()
            )


            Glide.with(holder.itemView.context).load(glideUrl).apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(object : DrawableImageViewTarget(holder.img) {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        holder.bind(
                            img = resource,
                            title = list[position].title,
                            popularity = list[position].popularity,
                            date = list[position].release_date
                        )
                    }
                })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result: List<Result>) {
        list = result
        notifyDataSetChanged()
    }

    fun startAnimation() {
        isLoading = true
        notifyDataSetChanged()
    }

    fun stopAnimation() {
        isLoading = false
        notifyDataSetChanged()
    }

}
