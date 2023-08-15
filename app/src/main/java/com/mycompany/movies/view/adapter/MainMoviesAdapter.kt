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
import com.mycompany.movies.databinding.ItemMainMoviesBinding
import com.mycompany.movies.model.Details
import com.mycompany.movies.util.Constraints
import com.mycompany.movies.util.OnClickListener
import com.mycompany.movies.view.viewholder.MainMoviesViewHolder

class MainMoviesAdapter : RecyclerView.Adapter<MainMoviesViewHolder>() {
    private var movies = listOf<Details>()
    private lateinit var listener: OnClickListener
    private var isLoading = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMoviesViewHolder {
        val item = ItemMainMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainMoviesViewHolder(item,listener)
    }

    override fun getItemCount(): Int {
        return if (isLoading) 4 else movies.size
    }


    override fun onBindViewHolder(
        holder: MainMoviesViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (isLoading) {
            holder.bindShimmer()
        } else {
            val imgUrl =
                Constraints.URL_BASE_IMG + movies[position].poster?.dropLast(3) + "svg"

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
                        holder.bind(
                            title = movies[position].name,
                            img = resource,
                            code = movies[position].code
                        )
                    }
                })
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(list: List<Details>) {
        isLoading = list.isEmpty()
        movies = list
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun stopShimmer(){
        isLoading = false
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun start() {
        isLoading = true
        notifyDataSetChanged()

    }

    fun setOnClick(listener: OnClickListener) {
        this.listener = listener
    }
}
