package com.mycompany.movies.view.viewholder

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.movies.databinding.RowSearchBinding
import com.mycompany.movies.util.OnClickListener
import java.text.SimpleDateFormat

class SearchViewHolder(private val binding: RowSearchBinding, private val listener: OnClickListener) : RecyclerView.ViewHolder(binding.root) {

   private val img = binding.imagePoster

    @SuppressLint("SimpleDateFormat")
    fun bind(code:Int, img: Drawable?, title: String, popularity: Double, date: String){
        if (img!= null) {
            binding.imagePoster.setImageDrawable(img)
            binding.textNameMovies.text = title
            binding.textStart.text = popularity.toString()

            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")

            val formatDate = inputFormat.parse(date)
            val outputDate = outputFormat.format(formatDate!!)

            binding.textCalendar.text = outputDate
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
