package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.Movies
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val _list = MutableLiveData<Movies>()
    val list :LiveData<Movies> = _list
    private val repository = MoviesRepository()


    fun moviesPopular() {
        repository.getMoviesPopular(listener = object :ApiResponse<Movies>{
            override fun success(result: Movies) {
                if (result.results.isEmpty()){
                    _list.value = result
                }
            }

            override fun failure(message: String) {
                val s =""
            }

        })
    }

}