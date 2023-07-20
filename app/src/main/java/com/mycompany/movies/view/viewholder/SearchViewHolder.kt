package com.mycompany.movies.view.viewholder

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowSearchBinding
import com.mycompany.movies.util.OnClickListener

class SearchViewHolder(private val binding: RowSearchBinding, private val listener: OnClickListener) : RecyclerView.ViewHolder(binding.root) {

   private val img = binding.imagePoster

    fun bind(code:Int, img: Drawable?, title: String, popularity: Double, date: String){
        if (img!= null){
            binding.imagePoster.setImageDrawable(img)
            binding.textNameMovies.text = title
            binding.textStart.text = popularity.toString()
            binding.textCalendar.text = date
        }else{
            binding.imageCalendar.setImageDrawable(null)
            binding.textNameMovies.text = ""
        }

        binding.textNameMovies.setOnClickListener {
            listener.onClick(code)
        }
    }

    fun returnImg (): AppCompatImageView{
        return img
    }


}
