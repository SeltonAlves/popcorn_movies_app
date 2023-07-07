package com.mycompany.movies.view.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.ItemMainMoviesBinding
import com.mycompany.movies.view.viewholder.FutureMoviesViewHolder

class MainMoviesAdapter : RecyclerView.Adapter<FutureMoviesViewHolder>() {
    private var movies = listOf<Pair<String, Bitmap?>>()
    private var isLoading = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureMoviesViewHolder {
        val item = ItemMainMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FutureMoviesViewHolder(item)
    }

    override fun getItemCount(): Int {
        return if (isLoading) 2 else movies.count()
    }


    override fun onBindViewHolder(holder: FutureMoviesViewHolder, position: Int) {
        if (isLoading){
            holder.bindShimmer()
        }else{
            holder.bind(movies[position])
            holder.hideShimmer()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(list: List<Pair<String, Bitmap?>>) {
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
}