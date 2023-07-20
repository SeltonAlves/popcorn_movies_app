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
import com.mycompany.movies.util.Constraints
import com.mycompany.movies.util.OnClickListener
import com.mycompany.movies.view.viewholder.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var list = listOf<Result>()
    private lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item = RowSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(item,listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
            val imgUrl =
                Constraints.URL_BASE_IMG + list[position].poster_path?.dropLast(3) + "svg"

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
                            code = list[position].id,
                            img = resource,
                            title = list[position].title,
                            popularity = list[position].popularity,
                            date = list[position].release_date
                        )
                    }
                })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result: List<Result>) {
        list = result
        notifyDataSetChanged()
    }

    fun onClick(click: OnClickListener) {
        listener = click
    }

}
