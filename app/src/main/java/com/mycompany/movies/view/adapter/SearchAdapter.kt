package com.mycompany.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowSearchBinding
import com.mycompany.movies.model.Result
import com.mycompany.movies.view.viewholder.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var list = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item = RowSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(item)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(result: List<Result>) {
        list = result
    }

}
