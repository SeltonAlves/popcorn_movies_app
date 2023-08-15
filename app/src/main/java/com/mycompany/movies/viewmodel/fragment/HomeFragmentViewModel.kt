package com.mycompany.movies.viewmodel.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycompany.movies.model.Details
import com.mycompany.movies.request.repository.MoviesRepository
import com.mycompany.movies.request.service.ApiResponse

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MoviesRepository()
    private val _movies = MutableLiveData<List<Details>>()
    val list: LiveData<List<Details>> = _movies
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun moviesPopular() {
        _isLoading.value = true
        repository.getMoviesPopular(object : ApiResponse<List<Details>> {
            override fun success(result: List<Details>) {
                if (result.isNotEmpty()) {
                    _movies.value = result.take(10)
                    _isLoading.value = false
                }
            }

            override fun failure(message: String) {
                _error.value = message
                _isLoading.value = false
            }

        })

    }
}