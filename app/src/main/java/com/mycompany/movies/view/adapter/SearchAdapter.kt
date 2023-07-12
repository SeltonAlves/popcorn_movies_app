package com.mycompany.movies.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.model.Result
import com.mycompany.movies.view.viewholder.SearchViewHolder

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    private var list = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val item =

    }

    override fun getItemCount(): Int  = list.count()

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        TODO("SDFSDF")
    }

   fun setItem(result: List<Result>){
        list = result
    }

}
