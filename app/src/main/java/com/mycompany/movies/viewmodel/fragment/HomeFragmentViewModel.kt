package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.Movies
import com.mycompany.movies.model.Result
import com.mycompany.movies.model.ValidationModel
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository()
    private val _list = MutableLiveData<List<Bitmap?>>()
    val list: LiveData<List<Bitmap?>> = _list
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun moviesPopular() {
        repository.getMoviesPopular(object : ApiResponse<List<Bitmap?>>{
            override fun success(result: List<Bitmap?>) {
                if (result.isNotEmpty()){
                    _list.value = result
                }
            }
            override fun failure(message: String) {
                _error.value = message
            }

        })

    }

}